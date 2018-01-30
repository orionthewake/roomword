package com.raywenderlich.android.roomword.ui

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.raywenderlich.android.roomword.R
import com.raywenderlich.android.roomword.model.Word


class WordListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

  private val inflater: LayoutInflater = LayoutInflater.from(context)
  private var words: List<Word>? = null // Cached copy of words

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
    val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
    return WordViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
    if (words != null) {
      val current = words!![position]
      holder.wordItemView.text = current.word
    } else {
      // Covers the case of data not being ready yet.
      holder.wordItemView.text = holder.wordItemView.context.getString(R.string.no_word)
    }
  }

  // getItemCount() is called many times, and when it is first called,
  // words has not been updated (means initially, it's null, and we can't return null).
  override fun getItemCount(): Int {
    return if (words != null)
      words!!.size
    else
      0
  }

  fun setWords(words: List<Word>) {
    this.words = words
    notifyDataSetChanged()
  }

  inner class WordViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val wordItemView: TextView = itemView.findViewById(R.id.textView)
  }
}