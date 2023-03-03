package com.example.calculator.presentation.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.domain.useCases.CalculateAnswerUserCase
import com.example.calculator.domain.useCases.ChangeSignUseCase

class CalculatorModelFactory(context: Context): ViewModelProvider.Factory {

    private val calculateAnswerUserCase = CalculateAnswerUserCase()
    private val changeSignUseCase = ChangeSignUseCase()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CalculatorModel(calculateAnswerUserCase, changeSignUseCase) as T
    }
}