package com.raywenderlich.android.roomword.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.raywenderlich.android.roomword.model.Word
import com.raywenderlich.android.roomword.model.WordRepository


class WordViewModel(application: Application) : AndroidViewModel(application) {
  private val repository = WordRepository()
  private val allWords = repository.getAllWords()

  fun getAllWords() = allWords

  fun insert(word: Word) = repository.insert(word)
}