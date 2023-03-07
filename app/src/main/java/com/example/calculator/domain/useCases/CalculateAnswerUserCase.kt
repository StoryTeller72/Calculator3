package com.example.calculator.domain.useCases

import android.util.Log
import java.util.Stack
import kotlin.math.floor

class CalculateAnswerUserCase {

    fun execute(string: String): String{
        val equation = stringToListArray(string)
        val postfixEquation = convertPostfixNotation(equation)
        return calculateEquation(postfixEquation)
    }

    private fun stringToListArray(string: String): ArrayList<String>{
        val equation = string.replace(',', '.')
        var i = 0
        val arrayList = arrayListOf<String>()
        var temp =""
        while (i < equation.length){
            when(string[i]){
                in '0'..'9' -> temp += equation[i]
                ',' -> temp += '.'
                '(' ->{
                    i++
                    while (equation[i] != ')'){
                        temp += equation[i]
                        i++
                    }
                }
                '%' -> arrayList.add(equation[i].toString())
                else ->{
                    if(i == 0 && equation[i] == '-'){
                        temp += "-"
                        while (equation[i + 1].isDigit()){
                            temp += equation[i + 1]
                            i++
                        }
                    }else {
                        arrayList.add(temp)
                        temp = ""
                        arrayList.add(equation[i].toString())
                    }
                }
            }
            i++
        }
        if (temp.isNotEmpty())
            arrayList.add(temp)
        return arrayList
    }

    private fun convertPostfixNotation(arrayList: ArrayList<String>): ArrayList<String>{
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

    private fun calculateEquation(equation: ArrayList<String>): String{
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
                        if(a == 0.0) return "ERROR"
                        b / a
                    }
                }
                stack.push(tempAnswer)
            }
            Log.d("testMinus", stack.toString())
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