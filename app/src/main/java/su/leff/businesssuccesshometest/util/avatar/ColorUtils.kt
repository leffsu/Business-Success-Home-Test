package su.leff.businesssuccesshometest.util.avatar

import android.graphics.Color
import kotlin.random.Random

object ColorUtils {
    fun getRandomColor(): Int {
        val rnd = Random(System.currentTimeMillis())
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    fun createColor(red: Int, green: Int, blue: Int): Int{
        return Color.argb(255, red, green, blue)
    }
}