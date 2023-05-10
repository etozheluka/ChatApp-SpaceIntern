package com.space.chatapp.utils.extension

import android.content.res.ColorStateList
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes

fun View.setTint(colorRes:Int){
    this.backgroundTintList = ColorStateList.valueOf(this.context.getColor(colorRes))
}
fun TextView.setTextColorCompat(@ColorRes colorRes: Int) {
        this.setTextColor(context.getColor(colorRes))
}

