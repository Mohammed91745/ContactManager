package com.example.contactmanage

class ContactRepository(private val contactDao: ContactDao) {
    suspend fun insertContact(contact: Contact) = contactDao.insertContact(contact)
    suspend fun findContacts(name: String) = contactDao.findContacts(name)
    suspend fun deleteContact(id: Int) = contactDao.deleteContact(id)
    suspend fun sortContactsAsc() = contactDao.sortContactsAsc()
    suspend fun sortContactsDesc() = contactDao.sortContactsDesc()
    suspend fun getAllContacts() = contactDao.getAllContacts()
}
