package com.example.home25

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.home25.databinding.ActivitySecondTaskBinding
import kotlinx.coroutines.*

class SecondTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondTaskBinding
    private val myScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        motion()
    }

    fun motion() {

        var coordinateX = 0f
        var coordinateY = 0f

        binding.square.setOnClickListener {
            myScope.launch {
                while (binding.square.x < binding.cL.width - binding.square.width) {
                    delay(30)
                    coordinateX = coordinateX + 10f
                    coordinateY = coordinateY + 10f
                    withContext(Dispatchers.Main) {
                        binding.square.translationX = coordinateX
                        binding.square.translationY = -coordinateY
                    }
                }
                while (binding.square.x > 0f) {
                    delay(30)
                    coordinateX = coordinateX - 10f
                    coordinateY = coordinateY + 10f
                    withContext(Dispatchers.Main) {
                        binding.square.translationX = coordinateX
                        binding.square.translationY = -coordinateY
                    }
                }
                while (binding.square.x > 0f) {
                    delay(30)
                    coordinateX = coordinateX - 10f
                    coordinateY = coordinateY - 10f
                    withContext(Dispatchers.Main) {
                        binding.square.translationX = coordinateX
                        binding.square.translationY = -coordinateY
                    }
                }
            }
        }
    }
}
