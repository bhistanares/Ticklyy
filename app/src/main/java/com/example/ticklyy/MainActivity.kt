package com.example.ticklyy

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cardHomeTask = findViewById<LinearLayout>(R.id.cardHomeTask)
        val cardSchoolTask = findViewById<LinearLayout>(R.id.cardSchoolTask)
        val cardOtherTask = findViewById<LinearLayout>(R.id.cardOtherTask)

        val btnSearchBox = findViewById<LinearLayout>(R.id.btnSearchBox)
        val btnAddFolderBox = findViewById<LinearLayout>(R.id.btnAddFolderBox)
        val btnSettingBox = findViewById<LinearLayout>(R.id.btnSettingBox)

        cardHomeTask.setOnClickListener {
            Toast.makeText(this, "Folder Tugas Rumah diklik", Toast.LENGTH_SHORT).show()
        }

        cardSchoolTask.setOnClickListener {
            Toast.makeText(this, "Folder Tugas Sekolah diklik", Toast.LENGTH_SHORT).show()
        }

        cardOtherTask.setOnClickListener {
            Toast.makeText(this, "Folder Tugas Lainnya diklik", Toast.LENGTH_SHORT).show()
        }

        btnSearchBox.setOnClickListener {
            Toast.makeText(this, "Tombol Cari diklik", Toast.LENGTH_SHORT).show()
        }

        btnAddFolderBox.setOnClickListener {
            Toast.makeText(this, "Tombol Buat Folder diklik", Toast.LENGTH_SHORT).show()
        }

        btnSettingBox.setOnClickListener {
            Toast.makeText(this, "Tombol Setelan diklik", Toast.LENGTH_SHORT).show()
        }
    }
}