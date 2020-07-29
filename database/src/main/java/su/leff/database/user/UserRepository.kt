package su.leff.database.user

class UserRepository(private val userDao: UserDao) {

    fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    fun fetchAllUsers(): List<User> {
        return userDao.fetchAllUsers()
    }

    fun getUser(userId: Long): User {
        return userDao.getUser(userId)
    }
}