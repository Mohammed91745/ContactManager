package com.example.contactmanage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val contactId: Int = 0,
    val contactName: String,
    val contactPhone: String
)
