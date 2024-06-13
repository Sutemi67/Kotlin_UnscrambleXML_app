package com.example.unscramble_xml.logic

import android.content.Context
import android.view.View
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

fun pickWordAndShuffle(activity: MainActivity) {
    currentWord = allWords.random()
    if (usedWords.contains(currentWord)) {
        if (endCounter == allWords.size) {
            Toast.makeText(
                getActivity(activity),
                "Игра окончена - вы угадали все слова!",
                Toast.LENGTH_LONG
            ).show()
            activity.gameIsOn = false
            return
        }
        pickWordAndShuffle(activity)
    } else {
        endCounter++
        usedWords.add(currentWord)
        scrambleWord(currentWord)
    }
}
