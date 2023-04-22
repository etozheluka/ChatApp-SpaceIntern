package com.space.chatapp.utils.extension

import android.content.res.ColorStateList
import android.widget.TextView

fun TextView.setTint(color:Int){
    this.backgroundTintList = ColorStateList.valueOf(this.context.getColor(color))
}