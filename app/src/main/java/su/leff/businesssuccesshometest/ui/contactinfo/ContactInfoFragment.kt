package su.leff.businesssuccesshometest.ui.contactinfo

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.fragment.findNavController
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
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

            elementPhone.onClick {
                Dexter.withContext(requireContext())
                    .withPermission(Manifest.permission.CALL_PHONE)
                    .withListener(object : PermissionListener {
                        override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                            startActivity(
                                Intent(
                                    Intent.ACTION_CALL,
                                    Uri.parse("tel:${user.phone}")
                                )
                            )
                        }

                        override fun onPermissionRationaleShouldBeShown(
                            p0: PermissionRequest?,
                            p1: PermissionToken?
                        ) {
                            println(p0)
                        }

                        override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                            println(p0)
                        }

                    }).check()
            }

            elementWebsite.txvBody.text = user.websiteUrl
            elementWebsite.txvDescription.text = getString(R.string.website)

            elementWebsite.onClick {
                try {
                    CustomTabsIntent.Builder().build()
                        .launchUrl(requireContext(), Uri.parse("https://${user.websiteUrl}/"))
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.check_website),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

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