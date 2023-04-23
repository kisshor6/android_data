package com.example.data

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.data.MainActivity.Companion.viewModal

class FinalNewsTemplate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_news_template)

        val iHeadline = intent.getStringExtra("iHeadline")
        val idNews = intent.getIntExtra("id", 0)
        val iDescription = intent.getStringExtra("iDescription")
        val iCategory = intent.getStringExtra("iCategory")


        val fHeadline = findViewById<TextView>(R.id.iHeadline)
        val fDescription = findViewById<TextView>(R.id.iDesc)
        val fCategory = findViewById<TextView>(R.id.iCategory)

        val editButton = findViewById<ImageView>(R.id.editButton)
        val deleteButton = findViewById<ImageView>(R.id.deleteButton)

        fHeadline.text = iHeadline.toString()
        fDescription.text = iDescription.toString()
        fCategory.text = iCategory.toString()

        deleteButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure Do you want to delete this News")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    viewModal.deleteNews(idNews)
                    dialog.dismiss()
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show()

                }
                .setNegativeButton("NO"){dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
            val messageView = alert.findViewById<TextView>(android.R.id.message)
            messageView?.setTextColor(Color.WHITE)
        }

        editButton.setOnClickListener {
            val intent = Intent(this, editNews::class.java)
            intent.putExtra("id", idNews)
            intent.putExtra("name", iHeadline)
            intent.putExtra("description", iDescription)
            intent.putExtra("category", iCategory)
            startActivity(intent)
        }
    }
}