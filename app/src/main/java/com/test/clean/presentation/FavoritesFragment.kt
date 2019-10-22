package com.test.clean.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.clean.R
import com.test.clean.domain.OnPostsInteractionListener
import com.test.clean.presentation.viewmodel.PostsViewModel
import com.test.clean.presentation.viewmodel.PostsViewModelFactory
import com.test.clean.presentation.viewmodel.model.PostUI
import com.test.clean.presentation.adapters.PostsRecyclerViewAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject
    lateinit var postsViewModelFactory: PostsViewModelFactory
    private lateinit var postsViewModel: PostsViewModel

    private val loadingLayout by lazy {
        requireActivity().findViewById<ConstraintLayout>(R.id.loadingLayout)
    }

    private val favoritesRecyclerView by lazy {
        requireActivity().findViewById<RecyclerView>(R.id.favoritesRecyclerView)
    }

    private var listener: OnPostsInteractionListener? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        if (context is OnPostsInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnPostsFragmentInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postsViewModel = ViewModelProvider(
            requireActivity(), postsViewModelFactory
        ).get(PostsViewModel::class.java)

        postsViewModel.loadFavoritePosts()
        observeLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    private fun observeLiveData() {
        postsViewModel.favoritePosts.observe(requireActivity(), Observer(::onPostsReceived))
        postsViewModel.loading.observe(requireActivity(), Observer(::onLoadingStateReceived))
    }

    private fun onPostsReceived(postsUI: List<PostUI>) {
        populateRecyclerView(postsUI)
    }

    private fun populateRecyclerView(postsUI: List<PostUI>) = favoritesRecyclerView.run {
        layoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        adapter = PostsRecyclerViewAdapter(postsUI, listener)
        addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun onLoadingStateReceived(isLoading: Boolean) {
        loadingLayout.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }
}
