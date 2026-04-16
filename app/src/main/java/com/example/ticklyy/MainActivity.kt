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
            intent.putExtra("NOTE_CATEGORY", selectedNote.category)
            startActivity(intent)
        }

        listNotes.setOnItemLongClickListener { _, _, position, _ ->
            showDeleteDialog(position)
            true
        }

        btnAddNote.setOnClickListener {
            val intent = android.content.Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
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
            Note("Belajar Android", "Belajar layout, activity, dan intent.", "Tugas Sekolah"),
            Note("Tugas Matematika", "Kerjakan halaman 25 nomor 1 sampai 10.", "Tugas Sekolah"),
            Note("Belanja Bulanan", "Beli sabun, beras, telur, dan susu.", "Pribadi")
        )
    }
}