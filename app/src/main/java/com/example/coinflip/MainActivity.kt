package com.example.coinflip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import kotlin.math.round

class MainActivity : AppCompatActivity() {
//  Initialize variables and define them later
    private lateinit var coinImage: ImageView
    private lateinit var totalCount: TextView
    private lateinit var headsCount: TextView
    private lateinit var tailsCount: TextView
    private lateinit var headsPercent: TextView
    private lateinit var tailsPercent: TextView
    private lateinit var headsProgressBar: ProgressBar
    private lateinit var tailsProgressBar: ProgressBar
    private lateinit var simNumber: EditText
    private lateinit var simButton: Button

//  Variable for storing heads, tail, and totals count
    private var heads = 0
    private var tails = 0
    private var totals = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      Get reference to the switch and button
        val simSwitch: SwitchCompat = findViewById(R.id.main_activity_sw_simulate)
        val flipButton: Button = findViewById(R.id.main_activity_bt_flip)
        val resetButton: Button = findViewById(R.id.main_activity_bt_reset)
        simButton = findViewById(R.id.main_activity_bt_simulate)

//      Set listeners for button
        simSwitch.setOnCheckedChangeListener { buttonView, isChecked ->  enableSim(isChecked)}
        flipButton.setOnClickListener { flip() }
        resetButton.setOnClickListener { reset() }
        simButton.setOnClickListener { sim() }

//      Set values to remaining views
        coinImage = findViewById(R.id.main_activity_iv_logo)
        totalCount = findViewById(R.id.main_activity_tv_total_flips)
        headsCount = findViewById(R.id.main_activity_tv_total_heads)
        tailsCount = findViewById(R.id.main_activity_tv_total_tails)
        headsPercent = findViewById(R.id.main_activity_tv_heads_percentage)
        tailsPercent = findViewById(R.id.main_activity_tv_tails_percentage)
        headsProgressBar = findViewById(R.id.main_activity_pb_heads_percentage)
        tailsProgressBar = findViewById(R.id.main_activity_pb_tails_percentage)
        simNumber = findViewById(R.id.main_activity_et_simulate)

    }

//      Turn on/off the simulation mode
        private fun enableSim(onState: Boolean) {
//          Get the state of the switch
            if (onState) {
//              Test using logcat
//              Log.i("test", "switch is on!")
                simNumber.visibility = View.VISIBLE
                simButton.visibility = View.VISIBLE
            } else {
//              Log.i("test", "switch is off!")
                simNumber.visibility = View.INVISIBLE
                simButton.visibility = View.INVISIBLE
            }
        }

//      Simulate a single coin's flip
        private fun flip() {
            val randomNumber = (0..1).random()

            if(randomNumber == 0) {
                update("heads")
            } else {
                update("tails")
            }
        }

//      Update the UI based on the coin flip
        private fun update(coinValue: String) {
//          Set the correct image on out coin flip and increment textview
            if (coinValue == "heads") {
                heads++
                coinImage.setImageResource(R.drawable.ic_heads_icon)
            } else {
                tails++
                coinImage.setImageResource(R.drawable.ic_tails_icon)
            }
//          Increment total flips
            totals++

//          Update textview to show results of the flip
//          Access the string from the string resource using getString so its not hardcoded
            headsCount.text = getString(R.string.heads_count_text) + " " + heads
            tailsCount.text = getString(R.string.tails_count_text) + " " + tails
            totalCount.text = getString(R.string.totals_count_text) + " " + totals

//          Update progress statistics
            updateStatistics()
        }

//      Update the statistics UI based on the previous coin flip
        private fun updateStatistics() {
            var headsPercentageResult = 0.0
            var tailsPercentageResult = 0.0

            if (totals != 0) {
                headsPercentageResult = round((heads.toDouble()/totals) * 10000)/100
                tailsPercentageResult = round((tails.toDouble()/totals) * 10000)/100
            }


//          Set the text view to update the UI
            headsPercent.text = "Heads: $headsPercentageResult %"
            tailsPercent.text = "Taisl: $tailsPercentageResult %"

//          Update progress bars
            headsProgressBar.progress = headsPercentageResult.toInt()
            tailsProgressBar.progress = tailsPercentageResult.toInt()
        }

//      Reset the history of the flip simulation
        private fun reset() {
//          Change the image view back to default
            coinImage.setImageResource(R.drawable.ic_flip_icon)

//          Set all counter variable back to zero
            heads = 0
            tails = 0
            totals = 0

//          Update textview to show results
            headsCount.text = getString(R.string.heads_count_text) + " " + heads
            tailsCount.text = getString(R.string.tails_count_text) + " " + tails
            totalCount.text = getString(R.string.totals_count_text) + " " + totals

//          Update progress statistics
            updateStatistics()
        }


//      Run the coin simulation mode for a set number of flips
        private fun sim() {
//          Get number to simulate and clear editText
            var numberToSim = 1
            if (simNumber.text.toString() != "") {
                numberToSim = simNumber.text.toString().toInt()
            }

            simNumber.setText("")

//          Run the proper number of flips fo the simulation
            for(i in 1..numberToSim) {
                flip()
            }

//          hide the keyboard
            hideKeyboard()
        }

        private fun hideKeyboard() {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(coinImage.windowToken, 0)
        }
}