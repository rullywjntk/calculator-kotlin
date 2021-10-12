package com.rully.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.rully.calculator.databinding.ActivityMainBinding
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var lastNumeric: Boolean = false
    var lastDec: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun onDigit(view: View) {
        binding.tvInput.append((view as Button).text)
        lastNumeric = true
        Toast.makeText(this, "Button Works", Toast.LENGTH_LONG).show()
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOperator(binding.tvInput.text.toString())) {
            binding.tvInput.append((view as Button).text)
            lastNumeric = false
            lastDec = false
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = binding.tvInput.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    binding.tvInput.text = (one.toDouble() - two.toDouble()).toString()
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun isOperator(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

    fun onClear(view: View) {
        binding.tvInput.text = ""
    }

    fun onDec(view: View) {
        if (!(binding.tvInput.text.contains("."))) {
            binding.tvInput.append((view as Button).text)
        }

//        if (lastNumeric && !lastDec) {
//            binding.tvInput.append(".")
//            lastNumeric = false
//            lastDec = true
//        }
    }
}