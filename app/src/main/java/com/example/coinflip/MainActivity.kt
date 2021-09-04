package com.example.coinflip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val simButton: Button = findViewById(R.id.main_activity_bt_simulate)

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
            headsCount.text = "Total heads: $heads"
            tailsCount.text = "Total tails: $tails"
            totalCount.text = "Total counts: $totals"

//          Update progress statistics
            updateStatistics()
        }

//      Update the statistics UI based on the previous coin flip
        private fun updateStatistics() {
            val headsPercentageResult = round((heads.toDouble()/totals) * 100)
            val tailsPercentageResult = round((tails.toDouble()/totals) * 100)

//          Set the text view to update the UI
            headsPercent.text = "Heads: $headsPercentageResult %"
            tailsPercent.text = "Taisl: $tailsPercentageResult %"

//          Update progress bars
            headsProgressBar.progress = headsPercentageResult.toInt()
            tailsProgressBar.progress = tailsPercentageResult.toInt()
        }

//      Reset the history of the flip simulation
        private fun reset() {

        }

//      Run the coin simulation mode for a set number of flips
        private fun sim() {

        }
}