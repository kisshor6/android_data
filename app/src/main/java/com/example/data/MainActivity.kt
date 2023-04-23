package com.example.data
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), itemClicked {

    companion object{
        lateinit var viewModal: NewsViewModal
    }
    lateinit var recycler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolBar = findViewById<Toolbar>(R.id.my_toolBar)
        setSupportActionBar(toolBar)

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, addItem::class.java)
            startActivity(intent)
        }

        recycler = findViewById(R.id.recyclerOfNews)
        recycler.layoutManager = LinearLayoutManager(this)

        viewModal = ViewModelProvider(this).get(NewsViewModal::class.java)
        viewModal.allNews.observe(this, Observer {
            val adapter = NewsAdapter(this, it, this)
            recycler.adapter = adapter
        })
    }

    override fun onItemClickOfNews(news: News) {
        val intent = Intent(this, FinalNewsTemplate::class.java)
        intent.putExtra("iHeadline", news.headline)
        intent.putExtra("iDescription", news.description)
        intent.putExtra("iCategory", news.category)
        intent.putExtra("id", news.id)
        startActivity(intent)
    }

}

