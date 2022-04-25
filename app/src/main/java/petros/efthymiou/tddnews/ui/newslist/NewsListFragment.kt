package petros.efthymiou.tddnews.ui.newslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import petros.efthymiou.tddnews.databinding.FragmentNewsListBinding
import petros.efthymiou.tddnews.ui.newslist.placeholder.MyArticle

@AndroidEntryPoint
class NewsListFragment : Fragment() {
    private lateinit var binding: FragmentNewsListBinding
    private val viewModel: NewsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getNewsList()

        Log.d(TAG, "onCreate: ")
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.rvNewsList.apply {
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.newsList.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess && result.getOrNull() != null) {
                binding.rvNewsList.adapter = NewsListAdapter(result.getOrNull()!!) {
                    val article = it
                    findNavController().navigate(
                        NewsListFragmentDirections.actionNewsListFragmentToNewsDetailsFragment(
                            MyArticle(
                                imageUrl = article.urlToImage,
                                title = article.title,
                                description = article.description
                            )
                        )
                    )
                }
            } else {
                Snackbar.make(
                    binding.root,
                    result.exceptionOrNull().toString(),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }


        viewModel.loader.observe(viewLifecycleOwner) {
            when (it) {
                true -> binding.loader.visibility = View.VISIBLE
                else -> binding.loader.visibility = View.GONE
            }
        }

        Log.d(TAG, "onCreateView: ")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: ")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        Log.d(TAG, "onViewStateRestored: ")
    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()

        Log.d(TAG, "onStop: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.d(TAG, "onSaveInstanceState: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.d(TAG, "onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "onDestroy: ")
    }

    companion object{
        const val TAG = "NewsListFragLifecycle"
    }

}