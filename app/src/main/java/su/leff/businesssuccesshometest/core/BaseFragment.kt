package su.leff.businesssuccesshometest.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import su.leff.businesssuccesshometest.util.viewModelFactory
import su.leff.businesssuccesshometest.viewmodel.ContactViewModel
import su.leff.database.AppDatabase
import su.leff.database.user.UserRepository

open class BaseFragment : Fragment() {

    lateinit var contactViewModel: ContactViewModel
    lateinit var appDatabase: AppDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appDatabase = AppDatabase.getInstance(requireContext())
        contactViewModel = ViewModelProvider(this, viewModelFactory {
            ContactViewModel(
                UserRepository(appDatabase.userDAO())
            )
        }).get(ContactViewModel::class.java)
    }

}