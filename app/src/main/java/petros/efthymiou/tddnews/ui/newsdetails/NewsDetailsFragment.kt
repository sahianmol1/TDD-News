package petros.efthymiou.tddnews.ui.newsdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import petros.efthymiou.tddnews.R
import petros.efthymiou.tddnews.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : Fragment() {

    private lateinit var binding: FragmentNewsDetailsBinding
    private val args: NewsDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            Glide.with(view).load(args.article.imageUrl).into(imgNews)
            tvNewsTitle.text = args.article.title
            tvDescription.text = args.article.description
        }

        return view
    }

}