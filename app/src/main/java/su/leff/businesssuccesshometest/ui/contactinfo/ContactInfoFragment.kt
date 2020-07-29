package su.leff.businesssuccesshometest.ui.contactinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.element_avatar.*
import kotlinx.android.synthetic.main.element_profile_info.view.*
import kotlinx.android.synthetic.main.fragment_contactinfo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import su.leff.businesssuccesshometest.R
import su.leff.businesssuccesshometest.core.BaseFragment
import su.leff.businesssuccesshometest.util.goBack
import su.leff.businesssuccesshometest.util.onClick

class ContactInfoFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contactinfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getLong("userId")

        userId?.let {
            val user = contactViewModel.getUser(userId)

            cflAvatar.color = user.color
            txvAvatarInitials.text = user.initials

            elementFirstName.txvBody.text = user.firstName
            elementFirstName.txvDescription.text = getString(R.string.first_name)

            elementLastName.txvBody.text = user.lastName
            elementLastName.txvDescription.text = getString(R.string.last_name)

            elementPhone.txvBody.text = user.phone
            elementPhone.txvDescription.text = getString(R.string.phone)

            elementWebsite.txvBody.text = user.websiteUrl
            elementWebsite.txvDescription.text = getString(R.string.website)

            btnEditContact.onClick {
                val action =
                    ContactInfoFragmentDirections.actionContactInfoFragmentToContactEditFragment(
                        userId
                    )
                findNavController().navigate(action)
            }


        }

        imgBack.onClick {
            goBack()
        }
    }
}