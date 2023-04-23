package com.example.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModal(application: Application) :AndroidViewModel(application) {
    val allNews : LiveData<List<News>>
    var repository : NewsRepository

    init {
        val dao = NewsDatabase.getDatabase(application).getNewsDao()
        repository = NewsRepository(dao)
        allNews = repository.allNews
    }

    fun insertNews(news: News) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(news)
    }
    fun deleteNews(id: Int) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(id)
    }
    fun updateNews(news: News) = viewModelScope.launch ( Dispatchers.IO ){
        repository.update(news)
    }
}