package com.example.contactmanage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao {
    @Insert
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM contacts WHERE contactName LIKE '%' || :name || '%'")
    suspend fun findContacts(name: String): List<Contact>

    @Query("DELETE FROM contacts WHERE contactId = :id")
    suspend fun deleteContact(id: Int)

    @Query("SELECT * FROM contacts ORDER BY contactName ASC")
    suspend fun sortContactsAsc(): List<Contact>

    @Query("SELECT * FROM contacts ORDER BY contactName DESC")
    suspend fun sortContactsDesc(): List<Contact>

    @Query("SELECT * FROM contacts")
    suspend fun getAllContacts(): List<Contact>
}
