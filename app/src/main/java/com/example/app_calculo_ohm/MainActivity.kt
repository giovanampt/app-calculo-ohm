package com.example.app_calculo_ohm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        val mainView = findViewById<android.view.View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editVoltage = findViewById<EditText>(R.id.editVoltage)
        val editResistance = findViewById<EditText>(R.id.editResistance)
        val editCurrent = findViewById<EditText>(R.id.editCurrent)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        btnCalculate.setOnClickListener {
            val vStr = editVoltage.text.toString()
            val rStr = editResistance.text.toString()
            val iStr = editCurrent.text.toString()

            val v = vStr.toDoubleOrNull()
            val r = rStr.toDoubleOrNull()
            val i = iStr.toDoubleOrNull()

            when {
                v != null && r != null && i == null -> {
                    val result = v / r
                    txtResult.text = "Corrente (I) = %.2f A".format(result)
                }
                v != null && i != null && r == null -> {
                    val result = v / i
                    txtResult.text = "Resistência (R) = %.2f Ω".format(result)
                }
                r != null && i != null && v == null -> {
                    val result = r * i
                    txtResult.text = "Tensão (V) = %.2f V".format(result)
                }
                else -> {
                    Toast.makeText(this, "Preencha exatamente dois campos para calcular o terceiro.", Toast.LENGTH_SHORT).show()
                    txtResult.text = ""
                }
            }
        }
    }
}