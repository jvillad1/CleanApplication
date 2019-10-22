package com.test.clean.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.test.clean.R
import com.test.clean.domain.OnPostsInteractionListener
import com.test.clean.presentation.adapters.PostsPagerAdapter
import com.test.clean.presentation.viewmodel.PostsViewModel
import com.test.clean.presentation.viewmodel.PostsViewModelFactory
import com.test.clean.presentation.viewmodel.model.PostUI
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_posts.*
import timber.log.Timber
import javax.inject.Inject

class PostsActivity : AppCompatActivity(), OnPostsInteractionListener {

    @Inject
    lateinit var postsViewModelFactory: PostsViewModelFactory
    private lateinit var postsViewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        setSupportActionBar(toolbar)
        viewPager.adapter = PostsPagerAdapter(this, supportFragmentManager)
        tabs.setupWithViewPager(viewPager)

        postsViewModel = ViewModelProvider(
            this, postsViewModelFactory
        ).get(PostsViewModel::class.java)

        fab.setOnClickListener { view ->
            removePostsAction(view)
        }
    }

    private fun removePostsAction(view: View) =
        Snackbar.make(view, getString(R.string.remove_all_posts_message), Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.remove_all_posts_yes)) {
                postsViewModel.clearPosts()
            }.show()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_posts, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionReload -> {
                postsViewModel.loadPosts()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPostsClick(postUI: PostUI) {
        Timber.d(postUI.title)
        val postDetailsIntent = Intent(this, PostDetailsActivity::class.java).apply {
            putExtras(Bundle().apply { putParcelable(KEY_SELECTED_POST, postUI) })
        }
        startActivityForResult(postDetailsIntent, POST_DETAILS_REQUEST_CODE)
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_right)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                POST_DETAILS_REQUEST_CODE -> {
                    val postToUpdate = data.getParcelableExtra<PostUI>(KEY_SELECTED_POST)!!
                    postsViewModel.addAsFavorite(postToUpdate)
                }
            }
        }
    }

    companion object {

        const val KEY_SELECTED_POST = "selected_post"
        const val POST_DETAILS_REQUEST_CODE = 1
    }
}
