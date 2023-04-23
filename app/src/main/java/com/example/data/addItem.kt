package com.example.data

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.view.View
import android.widget.*
import com.example.data.MainActivity.Companion.viewModal

class addItem : AppCompatActivity() {

    companion object{
        const val PIC_PHOTO_REQUEST_CODE = 1
    }
    lateinit var headline: EditText
    lateinit var description: EditText
    lateinit var picture: TextView
    lateinit var category : String
    lateinit var selectPicture : Button
     var selectedItemPosition : Int = 0


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val spinner = findViewById<Spinner>(R.id.spinner)
        headline = findViewById(R.id.headLine)
        description = findViewById(R.id.description)
        picture = findViewById(R.id.picture)
        selectPicture = findViewById(R.id.selectPicture)
        val submit = findViewById<Button>(R.id.addNewsItem)

        selectPicture.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PIC_PHOTO_REQUEST_CODE)
        }

        val adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                category = parent?.getItemAtPosition(position) as String
                selectedItemPosition = spinner.selectedItemPosition

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "nothing",  Toast.LENGTH_SHORT).show()
            }
        }

        submit.setOnClickListener {
            if (headline.text != null && description.text != null && category != null){
                val getHeadline = headline.text.toString()
                val getDesc = description.text.toString()
                viewModal.insertNews(News(0,getHeadline, getDesc,  category))
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("position", selectedItemPosition)
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PIC_PHOTO_REQUEST_CODE && resultCode == RESULT_OK){
            val photoUri = data?.data

            photoUri?.let {
                val photoName = getFileNameFromUri(it)
                picture.text = photoName
            }
        }
    }

    private fun getFileNameFromUri(it: Uri): String {
        var result = ""
        if (it.scheme == "content"){
            val cursor = contentResolver.query(it, null, null, null, null)
            cursor?.let {
                if (it.moveToFirst()){
                    val column = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    result = cursor.getString(column)
                }
                cursor.close()
            }
        }
        else if (it.scheme == "file"){
            result = it.lastPathSegment ?: ""
        }
        return result
    }
}