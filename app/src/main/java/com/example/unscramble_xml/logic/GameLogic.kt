package com.example.unscramble_xml.logic

import com.example.unscramble_xml.data.allWords
import com.example.unscramble_xml.data.usedWords

lateinit var currentWord: String

fun scrambleWord(word: String): String {
    val word2 = word.toCharArray()
    word2.shuffle()
    while (String(word2) == word) {
        word2.shuffle()
    }
    return String(word2)
}

fun pickWordAndShuffle(): String {
    currentWord = allWords.random()
    return if (usedWords.contains(currentWord)) {
        pickWordAndShuffle()
    } else {
        usedWords.add(currentWord)
        scrambleWord(currentWord)
    }
}