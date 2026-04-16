package com.example.ticklyy

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.app.AlertDialog

class EditNoteActivity : AppCompatActivity() {

    private lateinit var inputEditNoteTitle: EditText
    private lateinit var inputEditNoteContent: EditText
    private lateinit var textEditCategory: TextView
    private lateinit var btnSaveEditNote: Button

    private var noteIndex: Int = -1
    private var noteCategory: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        inputEditNoteTitle = findViewById(R.id.inputEditNoteTitle)
        inputEditNoteContent = findViewById(R.id.inputEditNoteContent)
        textEditCategory = findViewById(R.id.textEditCategory)
        textEditCategory.setOnClickListener {
            showCategoryDialog()
        }
        btnSaveEditNote = findViewById(R.id.btnSaveEditNote)

        noteIndex = intent.getIntExtra("NOTE_INDEX", -1)

        val noteTitle = intent.getStringExtra("NOTE_TITLE") ?: ""
        val noteContent = intent.getStringExtra("NOTE_CONTENT") ?: ""
        noteCategory = intent.getStringExtra("NOTE_CATEGORY") ?: "Tanpa Kategori"

        inputEditNoteTitle.setText(noteTitle)
        inputEditNoteContent.setText(noteContent)
        textEditCategory.text = "Kategori: $noteCategory"

        btnSaveEditNote.setOnClickListener {
            saveEditNote()
        }
    }

    private fun saveEditNote() {
        val updatedTitle = inputEditNoteTitle.text.toString().trim().uppercase()
        val updatedContent = inputEditNoteContent.text.toString().trim()

        if (updatedTitle.isEmpty()) {
            inputEditNoteTitle.error = "Judul note tidak boleh kosong"
            inputEditNoteTitle.requestFocus()
            return
        }

        if (noteIndex != -1) {
            MainActivity.sharedNotes[noteIndex].title = updatedTitle
            MainActivity.sharedNotes[noteIndex].content = updatedContent
            MainActivity.sharedNotes[noteIndex].category = noteCategory

            Toast.makeText(this, "Note berhasil diupdate", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    private fun showCategoryDialog() {
        val categories = arrayOf(
            "Tugas Sekolah",
            "Tugas Rumah",
            "Pribadi",
            "Lainnya"
        )

        AlertDialog.Builder(this)
            .setTitle("Pilih Kategori")
            .setItems(categories) { _, which ->
                noteCategory = categories[which]
                textEditCategory.text = "Kategori: $noteCategory"
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}