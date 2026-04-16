package com.example.ticklyy

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddNoteActivity : AppCompatActivity() {

    private lateinit var inputNoteTitle: EditText
    private lateinit var inputNoteContent: EditText
    private lateinit var btnSaveNote: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        inputNoteTitle = findViewById(R.id.inputNoteTitle)
        inputNoteContent = findViewById(R.id.inputNoteContent)
        btnSaveNote = findViewById(R.id.btnSaveNote)

        btnSaveNote.setOnClickListener {
            showCategoryDialog()
        }
    }

    private fun showCategoryDialog() {
        val title = inputNoteTitle.text.toString().trim()
        val formattedTitle = title.split(" ").joinToString(" ") {
            it.replaceFirstChar { char -> char.uppercase() }
        }
        val content = inputNoteContent.text.toString().trim()

        if (title.isEmpty()) {
            inputNoteTitle.error = "Judul note tidak boleh kosong"
            inputNoteTitle.requestFocus()
            return
        }

        val categories = arrayOf(
            "Tugas Sekolah",
            "Tugas Rumah",
            "Pribadi",
            "Lainnya"
        )

        AlertDialog.Builder(this)
            .setTitle("Pilih Kategori")
            .setItems(categories) { _, which ->
                val selectedCategory = categories[which]

                MainActivity.sharedNotes.add(
                    Note(formattedTitle, content, selectedCategory)
                )

                Toast.makeText(
                    this,
                    "Note berhasil ditambahkan ke kategori $selectedCategory",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}