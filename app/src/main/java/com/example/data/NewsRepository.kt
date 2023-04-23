package com.example.data

import androidx.lifecycle.LiveData

class NewsRepository (private val newsDao: NewsDao){

    val allNews : LiveData<List<News>> = newsDao.getAllNotes()

    suspend fun insert(news: News){
        newsDao.insertNews(news)
    }
    suspend fun delete(id: Int){
        newsDao.deleteNews(id)
    }
    suspend fun update(news: News){
        newsDao.updateNews(news)
    }
}