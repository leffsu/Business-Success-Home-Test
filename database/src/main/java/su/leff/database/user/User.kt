package su.leff.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import su.leff.database.user.util.ColorUtils

@Entity(tableName = "user")
class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Long,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val websiteUrl: String,
    val avatarRed: Int,
    val avatarGreen: Int,
    val avatarBlue: Int
) {
    val color = ColorUtils.createColor(avatarRed, avatarGreen, avatarBlue)

    companion object {
        fun createUser(
            firstName: String,
            lastName: String,
            phone: String,
            websiteUrl: String,
            avatarRed: Int,
            avatarGreen: Int,
            avatarBlue: Int
        ): User {
            return User(
                0L,
                firstName,
                lastName,
                phone,
                websiteUrl,
                avatarRed,
                avatarGreen,
                avatarBlue
            )
        }
    }
}