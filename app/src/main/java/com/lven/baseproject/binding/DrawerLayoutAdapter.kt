package com.lven.baseproject.binding

import androidx.core.view.GravityCompat
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout

/**
 * 自定义一些功能，也可以参考TextViewBindingAdapter
 */
class DrawerLayoutAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("app:isOpenDrawer")
        fun isOpenDrawer(drawerLayout: DrawerLayout, isOpen: Boolean) {
            if (isOpen) {
                drawerLayout.openDrawer(GravityCompat.START)
            } else {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
    }
}