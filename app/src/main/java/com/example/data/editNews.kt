package com.example.data

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class editNews : AppCompatActivity() {

    lateinit var uHeadline: EditText
    lateinit var uDescription: EditText
    lateinit var uCategory : String
    lateinit var updateNews : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_news)

        val currentId = intent.getIntExtra("id", 0)
        val currentTitle = intent.getStringExtra("name")
        val currentDescription = intent.getStringExtra("description")

        uHeadline = findViewById(R.id.uHeadLine)
        uDescription = findViewById(R.id.uDescription)
        updateNews = findViewById(R.id.updateNewsItem)

        uHeadline.setText(currentTitle)
        uDescription.setText(currentDescription)
        val spinner = findViewById<Spinner>(R.id.uSpinner)

        val adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                uCategory = parent?.getItemAtPosition(position) as String
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "nothing",  Toast.LENGTH_SHORT).show()
            }
        }
        updateNews.setOnClickListener {
            if (uHeadline.text != null && uDescription.text != null && uCategory != null){
                val getHeadline = uHeadline.text.toString()
                val getDesc = uDescription.text.toString()
                val news = News(currentId, getHeadline, getDesc, uCategory)
                MainActivity.viewModal.updateNews(news)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Item Updated", Toast.LENGTH_SHORT).show()
            }
        }
    }
}