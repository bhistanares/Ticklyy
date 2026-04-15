package com.example.ticklyy

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textGreeting: TextView
    private lateinit var listNotes: ListView
    private lateinit var btnSearch: LinearLayout
    private lateinit var btnAddNote: LinearLayout
    private lateinit var btnProfile: LinearLayout

    private lateinit var adapter: ArrayAdapter<String>

    private val noteList = sharedNotes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textGreeting = findViewById(R.id.textGreeting)
        listNotes = findViewById(R.id.listNotes)
        btnSearch = findViewById(R.id.btnSearch)
        btnAddNote = findViewById(R.id.btnAddNote)
        btnProfile = findViewById(R.id.btnProfile)

        val username = "Hyu"
        textGreeting.text = "Hello, $username!"

        updateListView()

        listNotes.setOnItemClickListener { _, _, position, _ ->
            val selectedNote = noteList[position]

            val intent = Intent(this, DetailNoteActivity::class.java)
            intent.putExtra("NOTE_INDEX", position)
            intent.putExtra("NOTE_TITLE", selectedNote.title)
            intent.putExtra("NOTE_CONTENT", selectedNote.content)
            startActivity(intent)
        }

        listNotes.setOnItemLongClickListener { _, _, position, _ ->
            showDeleteDialog(position)
            true
        }

        btnAddNote.setOnClickListener {
            showAddNoteDialog()
        }

        btnSearch.setOnClickListener {
            Toast.makeText(this, "Fitur cari belum dibuat", Toast.LENGTH_SHORT).show()
        }

        btnProfile.setOnClickListener {
            Toast.makeText(this, "Halaman profil belum dibuat", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        updateListView()
    }

    private fun updateListView() {
        val titleList = noteList.map { it.title }

        adapter = ArrayAdapter(
            this,
            R.layout.item_note,
            R.id.textNoteItem,
            titleList
        )

        listNotes.adapter = adapter
    }

    private fun showAddNoteDialog() {
        val inputTitle = EditText(this)
        inputTitle.hint = "Masukkan judul note"

        AlertDialog.Builder(this)
            .setTitle("Tambah Note")
            .setView(inputTitle)
            .setPositiveButton("Lanjut") { _, _ ->
                val newTitle = inputTitle.text.toString().trim()

                if (newTitle.isNotEmpty()) {
                    showAddContentDialog(newTitle)
                } else {
                    Toast.makeText(this, "Judul note tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun showAddContentDialog(title: String) {
        val inputContent = EditText(this)
        inputContent.hint = "Masukkan isi note"
        inputContent.minLines = 4

        AlertDialog.Builder(this)
            .setTitle("Isi Note")
            .setView(inputContent)
            .setPositiveButton("Simpan") { _, _ ->
                val newContent = inputContent.text.toString().trim()

                noteList.add(Note(title, newContent))
                updateListView()

                Toast.makeText(this, "Note berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun showDeleteDialog(position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Hapus Note")
            .setMessage("Yakin ingin menghapus note \"${noteList[position].title}\"?")
            .setPositiveButton("Hapus") { _, _ ->
                noteList.removeAt(position)
                updateListView()
                Toast.makeText(this, "Note berhasil dihapus", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    companion object {
        val sharedNotes = arrayListOf(
            Note("Belajar Android", "Belajar layout, activity, dan intent."),
            Note("Tugas Matematika", "Kerjakan halaman 25 nomor 1 sampai 10."),
            Note("Belanja Bulanan", "Beli sabun, beras, telur, dan susu.")
        )
    }
}