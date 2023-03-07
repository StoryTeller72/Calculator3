package com.example.calculator.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.presentation.viewModels.CalculatorViewModel
import com.example.calculator.presentation.viewModels.CalculatorViewModelFactory

class MainActivity : AppCompatActivity() {

    private val calculatorViewModel: CalculatorViewModel by viewModels {
        CalculatorViewModelFactory(this)
    }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val numberButtons = listOf(
            binding.mainButtonZero,
            binding.mainButtonOne,
            binding.mainButtonTwo,
            binding.mainButtonThree,
            binding.mainButtonFour,
            binding.mainButtonFive,
            binding.mainButtonSix,
            binding.mainButtonSeven,
            binding.mainButtonEight,
            binding.mainButtonNine
        )

        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener{
                calculatorViewModel.addNumberLetter(index)
            }
        }


        calculatorViewModel.equation.observe(this, Observer {
            binding.mainTvOutput.text = it
        })

        val operationsButtons = mapOf(
            binding.mainButtonPercent to '%',
            binding.mainButtonPlus to '+',
            binding.mainButtonMinus to '-',
            binding.mainButtonMultiplication to '*',
            binding.mainButtonDivision to '/'
        )

        operationsButtons.forEach { (button, operationSymbol) ->
            button.setOnClickListener {
                calculatorViewModel.addOperationSymbol(operationSymbol)
            }
        }

        binding.mainButtonPoint.setOnClickListener {
           calculatorViewModel.addPoint()
        }

        binding.mainButtonAC.setOnClickListener {
           calculatorViewModel.clearOutput()
        }

        binding.mainBtnBackspace.setOnClickListener {
          calculatorViewModel.clearLastSymbol()
        }

        binding.mainButtonSign.setOnClickListener {
            calculatorViewModel.changeSign()
        }


        binding.mainButtonEqual.setOnClickListener {
           calculatorViewModel.calculate()
        }
    }

}