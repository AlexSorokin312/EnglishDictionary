package com.example.englishdictionary.fragmentNavigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainRouter(

    private val supportFragmentManager: FragmentManager,
    private val containerId: Int) {

    fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack("$containerId")
            .commit()
    }
}