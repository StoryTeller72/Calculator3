package com.example.calculator.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.useCases.CalculateAnswerUserCase
import com.example.calculator.domain.useCases.ChangeSignUseCase

class CalculatorModel(
    private val calculateAnswerUserCase:CalculateAnswerUserCase,
    private val changeSignUseCase: ChangeSignUseCase
): ViewModel() {

    private val _equation = MutableLiveData<String>()
    val equation: LiveData<String>
        get() = _equation

    private var hasOperation = false



    init {
        _equation.value = ""
    }

    fun addNumberLetter(symbol: Char){
        if( _equation.value?.isNotEmpty() == true &&
            (_equation.value?.last() == '%' || _equation.value?.last() == ')')){
            _equation.value +="*"
            hasOperation = true
        }
        _equation.value += symbol
    }

    fun addOperationSymbol(oper:Char){
        if(_equation.value?.isNotEmpty() == true) {
            if (!_equation.value!!.last().isDigit() && _equation.value!!.last() != ')'){
                _equation.value = _equation.value!!.dropLast(1)
            }
            _equation.value += oper
            hasOperation = true
        }
    }

    fun addPoint(){
        if(_equation.value?.isNotEmpty() == true &&_equation.value!!.last().isDigit() )
            _equation.value += ','
    }

    fun clearOutput(){
        _equation.value = ""
    }

    fun clearLastSymbol(){
        if (_equation.value?.isNotEmpty() == true){
            if (_equation.value!!.last() ==')'){
                _equation.value = equation.value?.let { changeSignUseCase.execute(it) }
            }else{
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
            _equation.value = calculateAnswerUserCase.execute(_equation.value!!)
            hasOperation = false
        }
    }

}