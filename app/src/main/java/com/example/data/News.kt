package com.example.data

import android.widget.EditText
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newsTable")
class News(

    @PrimaryKey(autoGenerate = true)
    var id : Int,

    @ColumnInfo(name = "title")
    val headline: String,

    @ColumnInfo(name = "description")
    val description: String,

//    @ColumnInfo(name = "img")
//    val imgData: String,

    @ColumnInfo(name = "category")
    val category: String

)

