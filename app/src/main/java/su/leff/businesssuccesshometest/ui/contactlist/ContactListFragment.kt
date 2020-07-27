package su.leff.businesssuccesshometest.ui.contactlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_contactlist.*
import su.leff.businesssuccesshometest.R
import su.leff.businesssuccesshometest.core.BaseFragment
import su.leff.businesssuccesshometest.ui.contactinfo.ContactInfoFragment
import su.leff.database.user.User
import su.leff.database.user.util.ColorUtils
import su.leff.database.user.util.ColorUtils.getRandomColorInRGB

class ContactListFragment : BaseFragment() {

    private val contactAdapter = ContactListAdapter(this::goToChat)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contactlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvUsers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvUsers.adapter = contactAdapter

        val list = ArrayList<User>()
        ColorUtils.getRandomColor()
        val color = getRandomColorInRGB()
        list.add(User(0L, "Lev", "Nazarov", "89300057950", "leff.su", color[0], color[1], color[2]))
        val color1 = getRandomColorInRGB()
        list.add(
            User(
                0L,
                "Lev",
                "Nazarov",
                "89300057950",
                "leff.su",
                color1[0],
                color1[1],
                color1[2]
            )
        )

        contactAdapter.setList(list)
    }


    private fun goToChat(user: User) {
        val action = ContactListFragmentDirections.actionContactListFragmentToContactInfoFragment(user.userId)
        findNavController().navigate(action)
    }

}