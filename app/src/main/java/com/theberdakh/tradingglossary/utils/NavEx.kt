package com.theberdakh.tradingglossary.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun replaceFragment(
    fragmentManager: FragmentManager,
    @IdRes fragmentContainer: Int,
    fragment: Fragment
) {
    val transaction = fragmentManager.beginTransaction()
    transaction.setCustomAnimations(
        android.R.anim.fade_in,
        android.R.anim.fade_out,
        android.R.anim.fade_in,
        android.R.anim.fade_out
    )
    transaction.replace(fragmentContainer, fragment)
    transaction.commit()
}

fun addFragmentToBackStack(
    fragmentManager: FragmentManager,
    @IdRes fragmentContainer: Int,
    fragment: Fragment
) {
    val fragmentPopped = fragmentManager.popBackStackImmediate(fragment.tag, 0)
    if (!fragmentPopped && fragmentManager.findFragmentByTag(fragment.tag) == null) {
        val transaction = fragmentManager.beginTransaction()
        transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.add(fragmentContainer, fragment)
        transaction.commit()
    }
}

fun addFragment(
    fragmentManager: FragmentManager,
    @IdRes fragmentContainer: Int,
    fragment: Fragment
) {
    val transaction = fragmentManager.beginTransaction()
    transaction.addToBackStack(fragment.javaClass.simpleName)
    transaction.add(fragmentContainer, fragment)
    transaction.commit()
}