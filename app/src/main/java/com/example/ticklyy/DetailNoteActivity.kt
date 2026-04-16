package com.example.ticklyy

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailNoteActivity : AppCompatActivity() {

    private lateinit var textDetailTitle: TextView
    private lateinit var textDetailCategory: TextView
    private lateinit var textDetailContent: TextView
    private lateinit var btnEditNote: Button

    private var noteIndex: Int = -1
    private var noteTitle: String = ""
    private var noteContent: String = ""
    private var noteCategory: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_note)

        textDetailTitle = findViewById(R.id.textDetailTitle)
        textDetailCategory = findViewById(R.id.textDetailCategory)
        textDetailContent = findViewById(R.id.textDetailContent)
        btnEditNote = findViewById(R.id.btnEditNote)

        noteIndex = intent.getIntExtra("NOTE_INDEX", -1)
        noteTitle = intent.getStringExtra("NOTE_TITLE") ?: "Note Tidak Ditemukan"
        noteContent = intent.getStringExtra("NOTE_CONTENT") ?: ""
        noteCategory = intent.getStringExtra("NOTE_CATEGORY") ?: "Tanpa Kategori"

        textDetailTitle.text = noteTitle
        textDetailCategory.text = "Kategori: $noteCategory"
        textDetailContent.text = noteContent

        btnEditNote.setOnClickListener {
            val intent = android.content.Intent(this, EditNoteActivity::class.java)
            intent.putExtra("NOTE_INDEX", noteIndex)
            intent.putExtra("NOTE_TITLE", noteTitle)
            intent.putExtra("NOTE_CONTENT", noteContent)
            intent.putExtra("NOTE_CATEGORY", noteCategory)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        if (noteIndex != -1) {
            noteTitle = MainActivity.sharedNotes[noteIndex].title
            noteContent = MainActivity.sharedNotes[noteIndex].content
            noteCategory = MainActivity.sharedNotes[noteIndex].category

            textDetailTitle.text = noteTitle
            textDetailCategory.text = "Kategori: $noteCategory"
            textDetailContent.text = noteContent
        }
    }
}