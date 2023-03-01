package com.example.calculator.domain.useCases

class ChangeSignUseCase {

    fun execute(string: String): String{
        return changeSign(string)
    }

    private fun changeSign(string: String): String{
        if (string.isEmpty()) return ""
        var index = string.lastIndex
        var amntToDrop = 0
        var withoutLasNumber =""
        var temp =""
        if (string.last() ==')'){
            index--
            amntToDrop++
            while (index >= 0 && string[index] != '-') {
                index--
                amntToDrop++
            }
            withoutLasNumber = string.dropLast(amntToDrop+2)
            temp = string.takeLast(amntToDrop).dropLast(1)
        }
        else {
            while (index >= 0 && (string[index].isDigit() || string[index] == ',')) {
                index--
                amntToDrop++
            }
            withoutLasNumber = string.dropLast(amntToDrop)
            temp = "(-${string.takeLast(amntToDrop)})"
        }
        return withoutLasNumber + temp
    }
}