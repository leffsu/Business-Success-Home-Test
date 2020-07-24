package su.leff.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Long,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val websiteUrl: String,
    val avatarRed: Int,
    val avatarGreen: Int,
    val avatarBlue: Int
)