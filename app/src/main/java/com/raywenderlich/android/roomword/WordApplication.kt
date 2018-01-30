package com.raywenderlich.android.roomword

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.os.AsyncTask
import com.raywenderlich.android.roomword.model.Word
import com.raywenderlich.android.roomword.model.WordDao
import com.raywenderlich.android.roomword.model.WordRoomDatabase


class WordApplication : Application() {

  companion object {
    lateinit var database: WordRoomDatabase
  }

  override fun onCreate() {
    super.onCreate()
    database = Room.databaseBuilder(this, WordRoomDatabase::class.java, "word_database")
        .addCallback(roomDatabaseCallback).build()
  }

  private val roomDatabaseCallback = object : RoomDatabase.Callback() {
    override fun onOpen(db: SupportSQLiteDatabase) {
      super.onOpen(db)
      PopulateDbAsync(WordApplication.database).execute()
    }
  }

  private class PopulateDbAsync(db: WordRoomDatabase) : AsyncTask<Void, Void, Void>() {
    private val dao: WordDao = db.wordDao()

    override fun doInBackground(vararg params: Void): Void? {
      dao.deleteAll()
      var word = Word("Hello")
      dao.insert(word)
      word = Word("World")
      dao.insert(word)
      return null
    }
  }
}