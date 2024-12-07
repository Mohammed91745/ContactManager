package com.example.contactmanage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactListAdapter(private val onDeleteClick: (Int) -> Unit) :
    RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {

    private var contactList = listOf<Contact>()

    // Define the ViewHolder class
    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.contactName)
        val phone: TextView = itemView.findViewById(R.id.contactPhone)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]
        holder.name.text = contact.contactName
        holder.phone.text = contact.contactPhone
        holder.deleteButton.setOnClickListener { onDeleteClick(contact.contactId) }
    }

    override fun getItemCount(): Int = contactList.size

    fun setContacts(contacts: List<Contact>) {
        this.contactList = contacts
        notifyDataSetChanged()
    }
}

