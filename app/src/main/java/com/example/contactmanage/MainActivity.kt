package com.example.contactmanage

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ContactListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val addButton: Button = findViewById(R.id.addButton)
        val findButton: Button = findViewById(R.id.findButton)
        val ascButton: Button = findViewById(R.id.ascButton)
        val descButton: Button = findViewById(R.id.descButton)
        val nameInput: EditText = findViewById(R.id.nameInput)
        val phoneInput: EditText = findViewById(R.id.phoneInput)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        adapter = ContactListAdapter { id ->
            viewModel.deleteContact(id)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.allContacts.observe(this, {
            adapter.setContacts(it)
        })

        addButton.setOnClickListener {
            val name = nameInput.text.toString()
            val phone = phoneInput.text.toString()
            if (name.isNotBlank() && phone.isNotBlank()) {
                viewModel.addContact(Contact(contactName = name, contactPhone = phone))
                nameInput.text.clear()
                phoneInput.text.clear()
            } else {
                showToast("Please enter a name and phone number")
            }
        }

        findButton.setOnClickListener {
            val search = nameInput.text.toString()
            if (search.isNotBlank()) {
                viewModel.findContacts(search)
            } else {
                showToast("Please enter search criteria")
            }
        }

        ascButton.setOnClickListener { viewModel.sortContactsAsc() }
        descButton.setOnClickListener { viewModel.sortContactsDesc() }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
