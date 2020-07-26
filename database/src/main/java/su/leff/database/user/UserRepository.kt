package su.leff.database.user

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun fetchAllUsers(): List<User> {
        return userDao.fetchAllUsers()
    }

    suspend fun getUser(userId: Long): User {
        return userDao.getUser(userId)
    }

    suspend fun updateUser(user: User) {
        return userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        return userDao.deleteUser(user)
    }
}