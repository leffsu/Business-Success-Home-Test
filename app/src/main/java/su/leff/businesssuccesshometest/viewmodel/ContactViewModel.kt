package su.leff.businesssuccesshometest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import su.leff.database.user.User
import su.leff.database.user.UserRepository

class ContactViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val users = MutableLiveData<List<User>>()

    val allUsers: LiveData<List<User>> = users

    fun updateUsers(){
        users.postValue(userRepository.fetchAllUsers())
    }

    fun insertUser(user: User) {
        userRepository.insertUser(user)
    }

    fun getUser(userId: Long): User {
        return userRepository.getUser(userId)
    }
}