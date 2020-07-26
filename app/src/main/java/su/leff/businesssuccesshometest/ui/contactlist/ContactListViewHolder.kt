package su.leff.businesssuccesshometest.ui.contactlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_contact.view.*
import su.leff.database.user.User

class ContactListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(user: User) {
        view.txvContactName.text = "${user.firstName} ${user.lastName}"
        view.txvContactPhone.text = user.phone

    }
}