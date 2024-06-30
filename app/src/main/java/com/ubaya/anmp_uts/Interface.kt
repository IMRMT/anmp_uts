package com.ubaya.anmp_uts

import android.view.View

interface BeritaClickListener {
    fun onBeritaClick(v: View)
}
interface NextClickListener {
    fun onNextClick(v: View)
}
interface PrevClickListener {
    fun onPrevClick(v: View)
}

interface ButtonLogoutListener {
    fun onButtonLogoutClick(v: View)
}

interface ButtonLoginListener {
    fun onButtonLoginClick(v: View)
}

interface ButtonSignUpListener {
    fun onButtonSignUpClick(v: View)
}