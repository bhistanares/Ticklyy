package com.example.ticklyy

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailNoteActivity : AppCompatActivity() {

    private lateinit var textDetailTitle: TextView
    private lateinit var textDetailContent: TextView
    private lateinit var btnEditNote: Button

    private var noteIndex: Int = -1
    private var noteTitle: String = ""
    private var noteContent: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_note)

        textDetailTitle = findViewById(R.id.textDetailTitle)
        textDetailContent = findViewById(R.id.textDetailContent)
        btnEditNote = findViewById(R.id.btnEditNote)

        noteIndex = intent.getIntExtra("NOTE_INDEX", -1)
        noteTitle = intent.getStringExtra("NOTE_TITLE") ?: "Note Tidak Ditemukan"
        noteContent = intent.getStringExtra("NOTE_CONTENT") ?: ""

        textDetailTitle.text = noteTitle
        textDetailContent.text = noteContent

        btnEditNote.setOnClickListener {
            showEditDialog()
        }
    }

    private fun showEditDialog() {
        val inputTitle = EditText(this)
        inputTitle.setText(noteTitle)

        AlertDialog.Builder(this)
            .setTitle("Edit Judul")
            .setView(inputTitle)
            .setPositiveButton("Lanjut") { _, _ ->
                val updatedTitle = inputTitle.text.toString().trim()

                if (updatedTitle.isNotEmpty()) {
                    showEditContentDialog(updatedTitle)
                } else {
                    Toast.makeText(this, "Judul note tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun showEditContentDialog(updatedTitle: String) {
        val inputContent = EditText(this)
        inputContent.setText(noteContent)
        inputContent.minLines = 4

        AlertDialog.Builder(this)
            .setTitle("Edit Isi")
            .setView(inputContent)
            .setPositiveButton("Simpan") { _, _ ->
                val updatedContent = inputContent.text.toString().trim()

                if (noteIndex != -1) {
                    MainActivity.sharedNotes[noteIndex].title = updatedTitle
                    MainActivity.sharedNotes[noteIndex].content = updatedContent

                    noteTitle = updatedTitle
                    noteContent = updatedContent

                    textDetailTitle.text = noteTitle
                    textDetailContent.text = noteContent

                    Toast.makeText(this, "Note berhasil diupdate", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}