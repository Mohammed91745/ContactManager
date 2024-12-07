package com.example.contactmanage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ContactRepository
    private val contactDao: ContactDao

    val allContacts: MutableLiveData<List<Contact>> = MutableLiveData()

    init {
        val database = ContactRoomDatabase.getDatabase(application)
        contactDao = database.contactDao()
        repository = ContactRepository(contactDao)
        fetchAllContacts()
    }

    fun fetchAllContacts() {
        viewModelScope.launch {
            allContacts.postValue(repository.getAllContacts())
        }
    }

    fun addContact(contact: Contact) {
        viewModelScope.launch {
            repository.insertContact(contact)
            fetchAllContacts()
        }
    }

    fun findContacts(name: String) {
        viewModelScope.launch {
            allContacts.postValue(repository.findContacts(name))
        }
    }

    fun deleteContact(id: Int) {
        viewModelScope.launch {
            repository.deleteContact(id)
            fetchAllContacts()
        }
    }

    fun sortContactsAsc() {
        viewModelScope.launch {
            allContacts.postValue(repository.sortContactsAsc())
        }
    }

    fun sortContactsDesc() {
        viewModelScope.launch {
            allContacts.postValue(repository.sortContactsDesc())
        }
    }
}