package su.leff.businesssuccesshometest.util

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun <T : View> T.showIf(condition: (T) -> Boolean): T {
    if (condition(this)) {
        show()
    } else {
        hide()
    }

    return this
}

fun <T : View> T.hideIf(condition: (T) -> Boolean): T {
    if (condition(this)) {
        hide()
    } else {
        show()
    }

    return this
}

inline fun View.onClick(crossinline click: (View) -> Unit) {
    setOnClickListener { view ->
        click(view)
    }
}

inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>):T = f() as T
    }