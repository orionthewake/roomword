package com.raywenderlich.android.roomword.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(@PrimaryKey val word: String)