package su.leff.businesssuccesshometest.util.avatar

import su.leff.database.entity.User


object AvatarUtils {
    fun generateAvatar(user: User): AvatarPayload {
        val initials = "${user.firstName}${user.lastName}"
        val color = ColorUtils.createColor(user.avatarRed, user.avatarGreen, user.avatarBlue)
        return AvatarPayload(initials, color)
    }
}