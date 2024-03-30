package com.simple.challenge2_synergy

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var costOfServiceEditText: EditText
    private lateinit var serviceQualityRadioGroup: RadioGroup
    private lateinit var roundUpSwitch: Switch
    private lateinit var calculateButton: Button
    private lateinit var tipResultTextView: TextView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Calculator Tip"

        costOfServiceEditText = findViewById(R.id.cost_of_service)
        serviceQualityRadioGroup = findViewById(R.id.tip_options)
        roundUpSwitch = findViewById(R.id.round_up_switch)
        calculateButton = findViewById(R.id.calculate_button)
        tipResultTextView = findViewById(R.id.tip_result)

        calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val cost = costOfServiceEditText.text.toString().toDoubleOrNull()

        val selectedRadioButtonId = serviceQualityRadioGroup.checkedRadioButtonId
        val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
        val tipPercentage = when (selectedRadioButton?.id) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            R.id.option_ten_percent -> 0.1
            else -> 0.0
        }

        if (cost == null || cost <= 0) {
            Toast.makeText(this, "Please enter a valid cost", Toast.LENGTH_SHORT).show()
            return
        }

        var tipAmount = cost * tipPercentage
        if (roundUpSwitch.isChecked) {
            tipAmount = kotlin.math.ceil(tipAmount)
        }
        displayTip(tipAmount)
    }

    private fun displayTip(tipAmount: Double) {
        val formattedTip = DecimalFormat("#.##").format(tipAmount)
        tipResultTextView.text = "Tip Amount: $formattedTip"
    }
}
