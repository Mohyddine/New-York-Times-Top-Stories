package com.mehyo.nyttopstories.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
//Database Model
@Parcelize
@Entity(tableName = "result_table", indices = [Index(value = [ "title" ], unique = true)])
data class Result(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val title: String,
        val abstract_article: String,
        val published_date: String,
        val multimedia_big:String,
        val multimedia_small:String,
        val short_url: String
): Parcelable
