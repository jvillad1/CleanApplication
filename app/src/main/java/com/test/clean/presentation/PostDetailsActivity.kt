package com.test.clean.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.test.clean.R
import com.test.clean.presentation.PostsActivity.Companion.KEY_SELECTED_POST
import com.test.clean.presentation.viewmodel.model.PostUI
import kotlinx.android.synthetic.main.activity_post_details.*
import timber.log.Timber

class PostDetailsActivity : AppCompatActivity() {

    private lateinit var selectedPost: PostUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        selectedPost = intent.extras?.getParcelable(KEY_SELECTED_POST)!!
        Timber.d("Selected post is favorite? ${selectedPost.favorite}")

        showPostDetails(selectedPost)
    }

    private fun showPostDetails(selectedPost: PostUI) {
        with(selectedPost) {
            descriptionTextView.text = body
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_post_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.actionFavorite -> {
                if (selectedPost.favorite) {
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_outline)
                } else {
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
                }

                selectedPost.favorite = !selectedPost.favorite
                val postDetailsIntent = Intent().apply {
                    putExtras(Bundle().apply { putParcelable(KEY_SELECTED_POST, selectedPost) })
                }
                setResult(Activity.RESULT_OK, postDetailsIntent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_left)
    }
}
