package com.example.calculator.domain.useCases

import java.util.Stack
import kotlin.math.floor

class CalculateAnswerUserCase {

    fun execute(string: String): String{
        val equation = stringToListArray(string)
        val postfixEquation = convertPostfixNotation(equation)
        return calculateEquation(postfixEquation)
    }

    fun stringToListArray(string: String): ArrayList<String>{
        var i = 0
        val arrayList = arrayListOf<String>()
        var temp =""
        while (i < string.length){
            when(string[i]){
                in '0'..'9' -> temp += string[i]
                ',' -> temp += '.'
                '(' ->{
                    i++
                    while (string[i] != ')'){
                        temp += string[i]
                        i++
                    }
                }
                '%' -> arrayList.add(string[i].toString())
                else ->{
                    arrayList.add(temp)
                    temp = ""
                    arrayList.add(string[i].toString())
                }
            }
            i++
        }
        if (temp.isNotEmpty())
            arrayList.add(temp)
        return arrayList
    }

    fun convertPostfixNotation(arrayList: ArrayList<String>): ArrayList<String>{
        val priorityOper = hashMapOf("+" to 1, "-" to 1, "*" to 2, "/" to 2, "%" to 3)
        val answer = arrayListOf<String>()
        val operationStack: Stack<String> = Stack()
        for (symbol in arrayList){
            if (symbol.toDoubleOrNull() != null){
                answer.add(symbol)
            }
            else{
                while (operationStack.isNotEmpty() && priorityOper[symbol]!! <= priorityOper[operationStack.peek()]!!){
                    answer += operationStack.pop()
                }
                operationStack.push(symbol)
            }
        }
        while (operationStack.isNotEmpty()){
            answer.add(operationStack.pop())
        }
        return answer
    }

    fun calculateEquation(equation: ArrayList<String>): String{
        val stack: Stack<Double> = Stack()
        for (symbol in equation){
            if (symbol.toDoubleOrNull() != null) stack.push(symbol.toDouble())
            else{
                if (symbol == "%"){
                    val a = stack.pop()
                    val answer = a/100
                    stack.push(answer)
                    continue
                }
                val a = stack.pop()
                val b = stack.pop()
                val tempAnswer = when(symbol){
                    "+" -> a + b
                    "-" -> b - a
                    "*" -> a * b
                    else ->{
                        if(a==0.0) return "ERROR"
                        b / a
                    }
                }
                stack.push(tempAnswer)
            }
        }
        val answer = stack.pop()
        val answerString: String =
        if (floor(answer) == answer){
            answer.toInt().toString()
        }else{
            answer.toString().replace(".",",")
        }
        return answerString
    }
}