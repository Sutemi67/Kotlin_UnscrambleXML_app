package com.example.unscramble_xml.logic

import android.annotation.SuppressLint
import android.widget.Toast
import com.example.unscramble_xml.MainActivity
import com.example.unscramble_xml.data.allWords
import com.example.unscramble_xml.data.usedWords
import com.google.android.material.internal.ContextUtils.getActivity

lateinit var currentWord: String
var endCounter: Int = 0

fun scrambleWord(word: String): String {
    val word2 = word.toCharArray()
    word2.shuffle()
    while (String(word2) == word) {
        word2.shuffle()
    }
    return String(word2)
}

@SuppressLint("RestrictedApi")
fun pickWordAndShuffle(activity: MainActivity): String {
    currentWord = allWords.random()
    return if (usedWords.contains(currentWord)) {
        if (endCounter == allWords.size) {
            Toast.makeText(
                getActivity(activity),
                "Игра окончена - вы угадали все слова!",
                Toast.LENGTH_LONG
            ).show()
            activity.gameIsOn = false
            return "END"
        }
        pickWordAndShuffle(activity)
    } else {
        endCounter++
        usedWords.add(currentWord)
        scrambleWord(currentWord)
    }
}
