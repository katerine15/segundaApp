package com.adso.segunadaapp.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.adso.segunadaapp.R
import com.adso.segunadaapp.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView

    //boton de recalcular
    private lateinit var btnReCalculate:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)

        val result:Double = intent.extras?.getDouble(IMC_KEY)?:-1.0
        initComponents()
        initUI(result)
        initListeners()
    }
    private fun initListeners(){
        btnReCalculate.setOnClickListener {
            onBackPressed()
        }
    }
    private fun initComponents(){
        tvIMC = findViewById(R.id.tvIMC)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }
    private fun initUI(result: Double){
        tvIMC.text = result.toString()
        when(result){
            in 0.0..18.0 ->{ //bajo peso

                tvResult.text = getString(R.string.title_under_weight)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_bajo))
                tvDescription.text = getString(R.string.description_under_weight)
            }
            in 20.0..25.0 ->{ //peso normal

                tvResult.text = getString(R.string.title_normal_weight)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_normal))
                tvDescription.text = getString(R.string.description_normal_weight)
            }
            in 26.00..30.0 ->{ //sobrepeso

                tvResult.text = getString(R.string.title_overweight)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.sobrepeso))
                tvDescription.text = getString(R.string.description_overweight)
            }
            in 31.00..99.00 ->{ //obesidad

                tvResult.text = getString(R.string.title_obesity)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                tvDescription.text = getString(R.string.description_obesity)
            }
            else -> {//error
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }
}