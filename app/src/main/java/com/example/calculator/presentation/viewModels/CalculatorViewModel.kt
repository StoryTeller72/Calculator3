package com.example.calculator.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.useCases.CalculateAnswerUserCase
import com.example.calculator.domain.useCases.ChangeSignUseCase
import kotlin.math.floor

class CalculatorViewModel(
    private val calculateAnswerUserCase:CalculateAnswerUserCase,
    private val changeSignUseCase: ChangeSignUseCase
): ViewModel() {

    private val _equation = MutableLiveData<String>()
    val equation: LiveData<String>
        get() = _equation

    private var hasOperation = false
    private var hasComma = false



    init {
        _equation.value = ""
    }

    fun addNumberLetter(symbol: Int){
        if( _equation.value?.isNotEmpty() == true &&
            (_equation.value?.last() == '%' || _equation.value?.last() == ')')){
            _equation.value +="*"
            hasOperation = true
        }
        _equation.value += symbol.toString()
    }

    fun addOperationSymbol(oper:Char){
        if(_equation.value?.isNotEmpty() == true) {
            if (!_equation.value!!.last().isDigit() && _equation.value!!.last() != ')'){
                _equation.value = _equation.value!!.dropLast(1)
            }
            _equation.value += oper
            hasOperation = true
            hasComma = false
        }
    }

    fun addPoint(){
        if(_equation.value?.isNotEmpty() == true && _equation.value!!.last().isDigit() && !hasComma) {
            _equation.value += ','
            hasComma = true
        }
    }

    fun clearOutput(){
        _equation.value = ""
        hasComma = false
        hasOperation = false
    }

    fun clearLastSymbol(){
        if (_equation.value?.isNotEmpty() == true){
            if (_equation.value!!.last() ==')'){
                _equation.value = equation.value?.let { changeSignUseCase.execute(it) }
            }
            else{
                if(_equation.value!!.last() == ',')
                    hasComma = false
                _equation.value = _equation.value?.dropLast(1)
            }
        }
    }

    fun changeSign(){
        if(_equation.value?.isNotEmpty() == true && _equation.value!!.last().isDigit()){
            _equation.value = changeSignUseCase.execute(_equation.value!!)
        }
    }

    fun calculate(){
        if (_equation.value?.isNotEmpty() == true &&
            (_equation.value!!.last().isDigit() || _equation.value!!.last() == ')' || _equation.value!!.last() == '%')
            && hasOperation){
            val answer =  calculateAnswerUserCase.execute(_equation.value!!)
            _equation.value = answer
            hasOperation = false
            hasComma =  answer.contains(',')
        }
    }

}