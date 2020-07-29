package su.leff.businesssuccesshometest.ui.contactedit

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import cz.ackee.phoneformatter.PhoneNumberFormatter
import kotlinx.android.synthetic.main.element_avatar.*
import kotlinx.android.synthetic.main.element_profile_editor.view.*
import kotlinx.android.synthetic.main.fragment_contactedit.*
import kotlinx.android.synthetic.main.fragment_contactinfo.elementFirstName
import kotlinx.android.synthetic.main.fragment_contactinfo.elementLastName
import kotlinx.android.synthetic.main.fragment_contactinfo.elementPhone
import kotlinx.android.synthetic.main.fragment_contactinfo.elementWebsite
import kotlinx.android.synthetic.main.fragment_contactinfo.imgBack
import su.leff.businesssuccesshometest.R
import su.leff.businesssuccesshometest.core.BaseFragment
import su.leff.businesssuccesshometest.util.goBack
import su.leff.businesssuccesshometest.util.onClick
import su.leff.database.user.User
import su.leff.database.user.util.ColorUtils
import java.util.*


class ContactEditFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contactedit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getLong("userId")

        fun fieldsAreNotInput(): Boolean {
            return elementFirstName.edtBody.text.toString().isEmpty() ||
                    elementLastName.edtBody.text.toString().isEmpty() ||
                    elementPhone.edtBody.text.toString().isEmpty() ||
                    elementWebsite.edtBody.text.toString().isEmpty()
        }


        elementFirstName.txvDescription.text = getString(R.string.first_name)
        elementLastName.txvDescription.text = getString(R.string.last_name)
        elementPhone.edtBody.inputType = InputType.TYPE_CLASS_PHONE
        elementPhone.txvDescription.text = getString(R.string.phone)
        elementWebsite.edtBody.inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
        elementWebsite.txvDescription.text = getString(R.string.website)

        if (userId != null && userId != -1L) {
            val user = contactViewModel.getUser(userId)

            cflAvatar.color = user.color
            txvAvatarInitials.text = user.initials

            elementFirstName.edtBody.setText(user.firstName)
            elementLastName.edtBody.setText(user.lastName)
            elementPhone.edtBody.setText(user.phone)
            elementWebsite.edtBody.setText(user.websiteUrl)

            btnSaveContact.onClick {

                if (fieldsAreNotInput()
                ) {
                    Toast.makeText(requireContext(), getString(R.string.fill_all_fields), Toast.LENGTH_LONG)
                        .show()
                } else {
                    val newUser = User(
                        user.userId,
                        elementFirstName.edtBody.text.toString(),
                        elementLastName.edtBody.text.toString(),
                        elementPhone.edtBody.text.toString(),
                        elementWebsite.edtBody.text.toString(),
                        user.avatarRed,
                        user.avatarGreen,
                        user.avatarBlue
                    )
                    contactViewModel.insertUser(newUser)
                    findNavController().navigateUp()
                }
            }
        } else {
            var randomColor = intArrayOf()

            fun generateNewColor() {
                randomColor = ColorUtils.getRandomColorInRGB()
                cflAvatar.color =
                    ColorUtils.createColor(randomColor[0], randomColor[1], randomColor[2])
            }

            generateNewColor()
            cflAvatar.onClick {
                generateNewColor()
            }

            var initialFirstName = ' '
            var initialLastName = ' '

            fun updateInitials() {
                txvAvatarInitials.text = "$initialFirstName$initialLastName"
            }

            val textFormatter = PhoneNumberFormatter(Locale.getDefault().country)
            elementPhone.edtBody.addTextChangedListener(textFormatter)


            elementFirstName.edtBody.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    p0?.let {
                        initialFirstName = p0.toString()[0].toUpperCase()
                        updateInitials()
                    }
                }
            })

            elementLastName.edtBody.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    p0?.let {
                        initialLastName = p0.toString()[0].toUpperCase()
                        updateInitials()
                    }
                }
            })

            btnSaveContact.onClick {
                if (fieldsAreNotInput()) {
                    Toast.makeText(requireContext(), getString(R.string.fill_all_fields), Toast.LENGTH_LONG)
                        .show()
                } else {
                    val newUser = User(
                        0L,
                        elementFirstName.edtBody.text.toString(),
                        elementLastName.edtBody.text.toString(),
                        elementPhone.edtBody.text.toString(),
                        elementWebsite.edtBody.text.toString(),
                        randomColor[0],
                        randomColor[1],
                        randomColor[2]
                    )

                    contactViewModel.insertUser(newUser)
                    findNavController().navigateUp()
                }
            }
        }

        btnCancel.onClick {
            goBack()
        }

        imgBack.onClick {
            goBack()
        }
    }
}