package su.leff.businesssuccesshometest.ui.contactlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_contactlist.*
import su.leff.businesssuccesshometest.R
import su.leff.businesssuccesshometest.core.BaseFragment
import su.leff.businesssuccesshometest.ui.contactedit.ContactEditFragmentDirections
import su.leff.businesssuccesshometest.util.onClick
import su.leff.database.user.User

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

        rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> fab.show()
                    else -> fab.hide()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        fab.onClick {
            val action =
                ContactListFragmentDirections.actionContactListFragmentToContactEditFragment(-1L)
            findNavController().navigate(action)
        }

        contactViewModel.updateUsers()
        contactViewModel.allUsers.observe(viewLifecycleOwner, Observer {
            contactAdapter.setList(it)
        })
    }

    private fun goToChat(user: User) {
        val action =
            ContactListFragmentDirections.actionContactListFragmentToContactInfoFragment(user.userId)
        findNavController().navigate(action)
    }
}