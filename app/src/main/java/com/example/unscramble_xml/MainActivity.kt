package com.example.unscramble_xml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unscramble_xml.logic.currentWord
import com.example.unscramble_xml.logic.pickWordAndShuffle

class MainActivity : AppCompatActivity() {
    var gameIsOn = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val answerWord = findViewById<TextView>(R.id.showWord)
        val input = findViewById<EditText>(R.id.inputTextField)
        val applyButton = findViewById<Button>(R.id.applyButton)
        val skipButton = findViewById<Button>(R.id.skipButton)
        val finishButton = findViewById<Button>(R.id.buttonFinish)
        val counterScore = findViewById<TextView>(R.id.counter)
        var counter = 0

        answerWord.text = pickWordAndShuffle(this)

        skipButton.setOnClickListener {
            pickWordAndShuffle(this)
        }
        applyButton.setOnClickListener {
            if (input.text.toString() == currentWord) {
                counter++
                counterScore.text = counter.toString()
                answerWord.text = pickWordAndShuffle(this)
                input.text.clear()
            } else {
                Toast.makeText(this, "Неверно!", 1).show()
                input.text.clear()
            }
        }

        if (!gameIsOn){
            skipButton.visibility = View.GONE
            applyButton.visibility = View.GONE
            finishButton.visibility = View.VISIBLE
            finishButton.setOnClickListener{finish()}
        }
    }
}

