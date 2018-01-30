package com.raywenderlich.android.roomword.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = [(Word::class)], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

  abstract fun wordDao(): WordDao
}