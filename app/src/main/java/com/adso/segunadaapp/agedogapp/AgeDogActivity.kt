package com.adso.segunadaapp.agedogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.adso.segunadaapp.R
import com.adso.segunadaapp.databinding.ActivityAgeDogBinding

class AgeDogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAgeDogBinding.inflate(layoutInflater)

        setContentView(binding.root)

        /*
        val age_edit = findViewById<EditText>(R.id.age_edit)
        val result_text = findViewById<TextView>(R.id.result_text)
        val btn_age = findViewById<Button>(R.id.btn_age)
         */
            binding.btnAge.setOnClickListener {
            val ageString = binding.ageEdit.text.toString()
            if (ageString.isNotEmpty()){
                val ageInt = ageString.toInt()
                val result = ageInt * 7
                binding.resultText.text = getString(R.string.result_text, result)
            }else{
                //alerta -> mensaje en el cual se notifica que se debe insertar la edad
                Toast.makeText(this,R.string.you_must_insert_your_age,Toast.LENGTH_SHORT).show()
            }
        }

    }
}