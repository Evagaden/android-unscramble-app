package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private var _score = 0
    private var currentWordCount = 0
    private var _currentScrambledWord = "test"

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    //Khai bao mot bien tra ve gia tri cua count co the duoc goi o ben ngoai class
    val count: Int get() = currentWordCount
    // tuong tu khi thay doi cho currentScambleWord
    val currentScrambledWord get() = _currentScrambledWord
    //Khai bao lai diem so
    val score get() = _score

    init
    {
        Log.d("GameViewModel", "GameViewModel created")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameViewModel", "GameViewModel destroyed")
    }

    private fun getNextWord()
    {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()
        while(String(tempWord).equals(currentWord,false))
        {
            tempWord.shuffle()
        }
        if(wordsList.contains(tempWord.toString()))
        {
            getNextWord()
        }
        else
        {
            _currentScrambledWord = String(tempWord)
            currentWordCount++
            wordsList.add(currentScrambledWord)
        }
    }
    /*
* Returns true if the current word count is less than MAX_NO_OF_WORDS.
* Updates the next word.
*/
    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }
    /* supporting for increasing score
     */
    private fun increaseScore()
    {
        _score += SCORE_INCREASE
    }
    /*
    check and increase users score if user is correct
     */
    fun isUserCorrectWord(playerWord: String): Boolean
    {
        if(playerWord.equals(currentWord, false))
        {
            increaseScore()
            return true
        }
        return false
    }

    fun restart()
    {
        _score = 0
        currentWordCount = 1
    }
}