package com.example.englishdictionary.behaviours

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomappbar.BottomAppBar

class BottomAppBarBehaviour(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is BottomAppBar
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        var bottomAppBar = dependency as BottomAppBar
        var currentBottomBarHeight = bottomAppBar.layoutParams.height + bottomAppBar.y
        val parentHeight = parent.layoutParams.height ?: 0
        child.layoutParams.height = (parentHeight + currentBottomBarHeight).toInt()
        child.requestLayout()
        return false
    }

}