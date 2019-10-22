package com.test.clean.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.clean.R
import com.test.clean.domain.OnPostsInteractionListener
import com.test.clean.presentation.viewmodel.model.PostUI
import kotlinx.android.synthetic.main.item_post.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class PostsRecyclerViewAdapter(
    private var values: List<PostUI>,
    private val listener: OnPostsInteractionListener?
) : RecyclerView.Adapter<PostsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bodyTextView.text = item.body

        holder.favoriteImageView.apply {
            visibility = if (item.favorite) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        with(holder.view) {
            tag = item

            setOnClickListener {
                listener?.onPostsClick(item)
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val bodyTextView: TextView = view.bodyTextView
        val favoriteImageView: ImageView = view.favoriteImageView

        override fun toString(): String {
            return super.toString() + " '" + bodyTextView.text + "'"
        }
    }
}
