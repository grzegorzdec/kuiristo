package com.grzegorzdec.kuiristo

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import com.grzegorzdec.kuiristo.ui.bottomappbar.CustomBottomAppBar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(bottom_bar)
        bottom_bar.fabAnimationMode = CustomBottomAppBar.FAB_ANIMATION_MODE_SLIDE

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_bottom_nav, menu)
        val searchItem = menu.findItem(R.id.menu_search).apply {
            val searchView = actionView as SearchView

            setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    detachFab()
                    searchView.isIconified = false
                    searchView.requestFocusFromTouch()
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    searchView.setQuery("", true)
                    searchView.hideKeyboard {
                        attachFab()
                    }
                    return true
                }
            })
        }
        return true
    }

    fun attachFab() {
        bottom_bar.fabAlignmentMode = CustomBottomAppBar.FAB_ALIGNMENT_MODE_CENTER
        val runnable = Runnable {
            bottom_bar.setFabAttached(true)
        }
        Handler().postDelayed(runnable, 150)
    }

    fun detachFab() {
        bottom_bar.setFabAttached(false)
        val runnable = Runnable {
            bottom_bar.fabAlignmentMode = CustomBottomAppBar.FAB_ALIGNMENT_MODE_END
        }
        Handler().postDelayed(runnable, 150)
    }

    fun View.hideKeyboard(function: () -> Unit) {
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(windowToken, 0)
        }

        val runnable = Runnable {
            function()
        }
        Handler().postDelayed(runnable, 150)
    }
}