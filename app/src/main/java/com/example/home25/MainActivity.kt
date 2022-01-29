package com.example.home25

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.DialogFragment
import com.example.home25.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var speedCar1: Long = 0
    var speedCar2: Long = 0
    var speedCar3: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        race()
    }

    class ResultFirstCar : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.setTitle("Results of the race!")
                    .setMessage("Won the third car!")
                    .setIcon(R.drawable.finish)
                    .setPositiveButton("Great!") { dialog, id ->
                        dialog.cancel()
                    }
                builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }

    class ResultSecondCar : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                val builder1 = AlertDialog.Builder(it)
                builder1.setTitle("Results of the race!")
                    .setMessage("Won the second car!")
                    .setIcon(R.drawable.finish)
                    .setPositiveButton("Great!") { dialog, id ->
                        dialog.cancel()
                    }
                builder1.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }

    class ResultThirdCar : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                val builder2 = AlertDialog.Builder(it)
                builder2.setTitle("Results of the race!")
                    .setMessage("Won the first car!")
                    .setIcon(R.drawable.finish)
                    .setPositiveButton("Great!") { dialog, id ->
                        dialog.cancel()
                    }
                builder2.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }

    fun race() {

        val resultFirstCar = ResultFirstCar()
        val resultSecondCar = ResultSecondCar()
        val resultThirdCar = ResultThirdCar()
        val manager = supportFragmentManager
        val manager1 = supportFragmentManager
        val manager2 = supportFragmentManager

        binding.button.setOnClickListener {
            speedCar1 = (1000..2000).random().toLong()
            speedCar2 = (1000..2000).random().toLong()
            speedCar3 = (1000..2000).random().toLong()
            binding.iVCar1.startAnimation(TranslateAnimation(0f, 0f, 0f, -1300f).apply {
                duration = speedCar3
                fillAfter = true
                if (speedCar2 > speedCar1 && speedCar1 < speedCar3){
                    showMessage(speedCar1){
                        resultFirstCar.show(manager,"mydialog1")
                    }
                }
            })

            binding.iVCar2.startAnimation(TranslateAnimation(0f, 0f, 0f, -1300f).apply {
                duration = speedCar1
                fillAfter = true
                if (speedCar1 > speedCar2 && speedCar2 < speedCar3){
                    showMessage(speedCar2){
                        resultSecondCar.show(manager1,"mydialog2")
                    }
                }
            })

            binding.iVCar3.startAnimation(TranslateAnimation(0f, 0f, 0f, -1300f).apply {
                duration = speedCar2
                fillAfter = true
                if (speedCar1 > speedCar3 && speedCar3 < speedCar2) {
                    showMessage(speedCar3) {
                        resultThirdCar.show(manager2, "mydialog3")
                    }
                }
            })
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondTaskActivity::class.java)
            startActivity(intent)
        }
    }


        companion object {
            fun showMessage(delay: Long, process: () -> Unit) {
                Handler().postDelayed({
                    process()
                }, delay)
            }
        }
    }






