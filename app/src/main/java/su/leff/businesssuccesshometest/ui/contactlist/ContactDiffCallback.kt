package su.leff.businesssuccesshometest.ui.contactlist

import androidx.recyclerview.widget.DiffUtil
import su.leff.database.user.User

class ContactDiffCallback(private val oldList: List<User>, private val newList: List<User>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].userId == newList[newItemPosition].userId
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].firstName == newList[newItemPosition].firstName &&
                oldList[oldItemPosition].lastName == newList[newItemPosition].lastName &&
                oldList[oldItemPosition].phone == newList[newItemPosition].phone &&
                oldList[oldItemPosition].websiteUrl == newList[newItemPosition].websiteUrl &&
                oldList[oldItemPosition].color == newList[newItemPosition].color
    }

}