package com.test.clean.presentation.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.test.clean.R
import com.test.clean.presentation.FavoritesFragment
import com.test.clean.presentation.PostsFragment
import timber.log.Timber

class PostsPagerAdapter(
    private val context: Context, fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        Timber.d(position.toString())

        return when (position) {
            0 -> PostsFragment.newInstance()
            1 -> FavoritesFragment.newInstance()
            else -> throw RuntimeException("$this Wrong fragment")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    companion object {

        private val TAB_TITLES = arrayOf(
            R.string.tab_all,
            R.string.tab_favorites
        )
    }
}
