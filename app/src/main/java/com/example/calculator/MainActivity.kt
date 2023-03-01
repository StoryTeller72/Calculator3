package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.domain.useCases.CalculateAnswerUserCase
import com.example.calculator.domain.useCases.ChangeSignUseCase

class MainActivity : AppCompatActivity() {

    private val calculateAnswerUserCase = CalculateAnswerUserCase()
    private val changeSignUseCase = ChangeSignUseCase()
    private lateinit var binding: ActivityMainBinding
    private var equation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainBtn1.setOnClickListener {
            if(equation.isNotEmpty() && equation.last() == '%'){
                equation+="*"
            }
            equation += "1"
            binding.mainTvOutput.text = equation
        }
        binding.mainBtn2.setOnClickListener {
            if(equation.isNotEmpty() && equation.last() == '%'){
                equation+="*"
            }
            equation += "2"
            binding.mainTvOutput.text = equation
        }
        binding.mainBtn3.setOnClickListener {
            if(equation.isNotEmpty() && equation.last() == '%'){
                equation+="*"
            }
            equation += "3"
            binding.mainTvOutput.text = equation
        }
        binding.mainBtn4.setOnClickListener {
            if(equation.isNotEmpty() && equation.last() == '%'){
                equation+="*"
            }
            equation += "4"
            binding.mainTvOutput.text = equation
        }
        binding.mainBtn5.setOnClickListener {
            if(equation.isNotEmpty() && equation.last() == '%'){
                equation+="*"
            }
            equation += "5"
            binding.mainTvOutput.text = equation
        }
        binding.mainBtn6.setOnClickListener {
            if(equation.isNotEmpty() && equation.last() == '%'){
                equation+="*"
            }
            equation += "6"
            binding.mainTvOutput.text = equation
        }
        binding.mainBtn7.setOnClickListener {
            if(equation.isNotEmpty() && equation.last() == '%'){
                equation+="*"
            }
            equation += "7"
            binding.mainTvOutput.text = equation
        }
        binding.mainBtn8.setOnClickListener {
            if(equation.isNotEmpty() && equation.last() == '%'){
                equation+="*"
            }
            equation += "8"
            binding.mainTvOutput.text = equation
        }
        binding.mainBtn9.setOnClickListener {
            if(equation.isNotEmpty() && equation.last() == '%'){
                equation+="*"
            }
            equation += "9"
            binding.mainTvOutput.text = equation
        }
        binding.mainBtnZero.setOnClickListener {
            if(equation.isNotEmpty() && equation.last() == '%'){
                equation+="*"
            }
            equation += "0"
            binding.mainTvOutput.text = equation
        }

        binding.mainBtnPlus.setOnClickListener {
            if(equation.isNotEmpty()) {
                equation += "+"
                binding.mainTvOutput.text = equation
            }
        }

        binding.mainBtnMinus.setOnClickListener {
            if(equation.isNotEmpty()) {
                equation += "-"
                binding.mainTvOutput.text = equation
            }
        }

        binding.mainBtnPercent.setOnClickListener {
            if(equation.isNotEmpty()) {
                equation += "%"
                binding.mainTvOutput.text = equation
            }
        }

        binding.mainBtnMult.setOnClickListener {
            if(equation.isNotEmpty()) {
                equation += "*"
                binding.mainTvOutput.text = equation
            }
        }


        binding.mainBtnDiv.setOnClickListener {
            if (equation.isNotEmpty()) {
                equation += "/"
                binding.mainTvOutput.text = equation
            }
        }

        binding.mainBtnAC.setOnClickListener {
            equation = ""
            binding.mainTvOutput.text = equation
        }

        binding.mainBtnBackspace.setOnClickListener {
            if(equation.isNotEmpty()) {
                equation = if(equation.last() == ')'){
                    changeSignUseCase.execute(equation)
                }else {
                    equation.dropLast(1)
                }
                binding.mainTvOutput.text = equation
            }
        }

        binding.mainBtnSign.setOnClickListener {
            if(equation.isNotEmpty() && equation.last().isDigit()) {
               equation = changeSignUseCase.execute(equation)
                binding.mainTvOutput.text = equation
            }

        }

        binding.mainBtnPoint.setOnClickListener {
            if (equation.isNotEmpty()) {
                equation += ","
                binding.mainTvOutput.text = equation
            }
        }

        binding.mainBtnEqual.setOnClickListener {
            if(equation.isNotEmpty()) {
                val answer = calculateAnswerUserCase.execute(equation)
                binding.mainTvOutput.text = answer
                equation = ""
            }
        }
    }
}