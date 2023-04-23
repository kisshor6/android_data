package com.example.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(news: News)

    @Update
    suspend fun updateNews(news: News)

    @Query("DELETE FROM newsTable WHERE id = :id")
    suspend fun deleteNews(id: Int)

    @Query("SELECT * FROM newsTable ORDER BY id ASC")
    fun getAllNotes() : LiveData<List<News>>
}