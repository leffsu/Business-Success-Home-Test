package su.leff.businesssuccesshometest.ui.contactinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import su.leff.businesssuccesshometest.R
import su.leff.businesssuccesshometest.core.BaseFragment

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
            GlobalScope.launch {
                val user = contactViewModel.getUser(userId)

            }
        }
    }
}