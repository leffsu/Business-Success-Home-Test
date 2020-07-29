package su.leff.businesssuccesshometest.ui.contactlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import su.leff.businesssuccesshometest.R
import su.leff.database.user.User

class ContactListAdapter(val goToChat: (user: User) -> Unit) :
    RecyclerView.Adapter<ContactListViewHolder>() {

    private val users = ArrayList<User>()

    fun setList(list: List<User>) {
        val result = DiffUtil.calculateDiff(ContactDiffCallback(users, list))
        users.clear()
        users.addAll(list)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_contact, parent, false)
        val viewHolder = ContactListViewHolder(v)
        v.setOnClickListener {
            goToChat(users[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        holder.bind(users[position])
    }
}