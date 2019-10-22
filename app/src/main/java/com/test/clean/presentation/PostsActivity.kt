package com.test.clean.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.clean.R
import dagger.android.AndroidInjection

class PostsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_posts)
    }
}
