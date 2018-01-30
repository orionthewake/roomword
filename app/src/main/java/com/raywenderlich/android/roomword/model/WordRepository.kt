package com.raywenderlich.android.roomword.model

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.raywenderlich.android.roomword.WordApplication


class WordRepository {
  private val wordDao: WordDao = WordApplication.database.wordDao()
  private val allWords: LiveData<List<Word>>

  init {
    allWords = wordDao.getAllWords()
  }

  fun getAllWords() = allWords

  fun insert(word: Word): AsyncTask<Word, Void, Void> = InsertAsyncTask(wordDao).execute(word)

  private class InsertAsyncTask internal constructor(private val dao: WordDao) : AsyncTask<Word, Void, Void>() {

    override fun doInBackground(vararg params: Word): Void? {
      dao.insert(params[0])
      return null
    }
  }
}