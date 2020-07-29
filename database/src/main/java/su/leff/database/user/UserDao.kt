package su.leff.database.user

import androidx.room.*
import su.leff.database.user.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM user")
    fun fetchAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE userId =:userId")
    fun getUser(userId: Long): User
}