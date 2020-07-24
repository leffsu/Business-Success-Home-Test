package su.leff.businesssuccesshometest.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

object IntentUtils {
    fun call(context: Context, phone: String){
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${phone}"))
        context.startActivity(intent)
    }

    fun openInBrowser(context: Context, websiteUrl: String){
        CustomTabsIntent.Builder()
            .build().launchUrl(context, Uri.parse(websiteUrl))
    }
}