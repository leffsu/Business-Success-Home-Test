package su.leff.database.user.util

import su.leff.database.user.User


object AvatarUtils {
    fun generateAvatar(user: User): AvatarPayload {
        val initials = "${user.firstName[0].toUpperCase()}${user.lastName[0].toUpperCase()}"
        val color = ColorUtils.createColor(user.avatarRed, user.avatarGreen, user.avatarBlue)
        return AvatarPayload(initials, color)
    }
}