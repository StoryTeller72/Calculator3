package com.example.calculator.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.presentation.viewModels.CalculatorModel
import com.example.calculator.presentation.viewModels.CalculatorModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CalculatorModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, CalculatorModelFactory(this))
            .get(CalculatorModel::class.java)

        viewModel.equation.observe(this, Observer {
            binding.mainTvOutput.text = it
        })

        binding.mainBtn1.setOnClickListener {
            viewModel.addNumberLetter('1')
        }
        binding.mainBtn2.setOnClickListener {
           viewModel.addNumberLetter('2')
        }
        binding.mainBtn3.setOnClickListener {
            viewModel.addNumberLetter('3')
        }
        binding.mainBtn4.setOnClickListener {
            viewModel.addNumberLetter('4')
        }
        binding.mainBtn5.setOnClickListener {
            viewModel.addNumberLetter('5')
        }
        binding.mainBtn6.setOnClickListener {
            viewModel.addNumberLetter('6')
        }
        binding.mainBtn7.setOnClickListener {
            viewModel.addNumberLetter('7')
        }
        binding.mainBtn8.setOnClickListener {
            viewModel.addNumberLetter('8')
        }
        binding.mainBtn9.setOnClickListener {
            viewModel.addNumberLetter('9')
        }
        binding.mainBtnZero.setOnClickListener {
            viewModel.addNumberLetter('0')
        }

        binding.mainBtnPlus.setOnClickListener {
           viewModel.addOperationSymbol('+')
        }

        binding.mainBtnMinus.setOnClickListener {
            viewModel.addOperationSymbol('-')
        }

        binding.mainBtnPercent.setOnClickListener {
            viewModel.addOperationSymbol('%')
        }

        binding.mainBtnMult.setOnClickListener {
            viewModel.addOperationSymbol('*')
        }


        binding.mainBtnDiv.setOnClickListener {
            viewModel.addOperationSymbol('/')
        }

        binding.mainBtnPoint.setOnClickListener {
           viewModel.addPoint()
        }

        binding.mainBtnAC.setOnClickListener {
           viewModel.clearOutput()
        }

        binding.mainBtnBackspace.setOnClickListener {
          viewModel.clearLastSymbol()
        }

        binding.mainBtnSign.setOnClickListener {
            viewModel.changeSign()
        }


        binding.mainBtnEqual.setOnClickListener {
           viewModel.calculate()
        }
    }

}