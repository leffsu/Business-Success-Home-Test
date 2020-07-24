package su.leff.database.dao

import androidx.room.*
import su.leff.database.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun fetchAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE userId =:userId")
    suspend fun getUser(userId: Long): User

    @Transaction
    @Update
    suspend fun updateUser(user: User)

    @Transaction
    @Delete
    suspend fun deleteUser(user: User)
}