package su.leff.businesssuccesshometest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import su.leff.database.user.User
import su.leff.database.user.UserRepository

class ContactViewModel(private val userRepository: UserRepository): ViewModel(){

    private val users = MutableLiveData<List<User>>()

    val allUsers: LiveData<List<User>> = users

    suspend fun allNotes() {
        users.postValue(userRepository.fetchAllUsers())
    }

    suspend fun insertUser(user: User) {
        userRepository.insertUser(user)
    }


    suspend fun updateUser(user: User) {
        userRepository.updateUser(user)
    }


    suspend fun deleteUser(user: User) {
        userRepository.deleteUser(user)
    }


    suspend fun getUser(userId: Long) {
        userRepository.getUser(userId)
    }
}