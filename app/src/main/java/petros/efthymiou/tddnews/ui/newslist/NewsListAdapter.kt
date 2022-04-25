package petros.efthymiou.tddnews.ui.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import petros.efthymiou.tddnews.data.models.Article
import petros.efthymiou.tddnews.databinding.ItemNewsListBinding

class NewsListAdapter(
    private val values: List<Article>,
    private val onClick: (Article) -> Unit
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemNewsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val imageView = binding.imgNews
        private val view = binding.root
        private val tvTitle = binding.tvNewsTitle
        private val tvDescription = binding.tvDescription

        fun bind(item: Article) {
            Glide.with(view).load(item.urlToImage).into(imageView)
            tvTitle.text = item.title
            tvDescription.text = item.description

            view.setOnClickListener {
                onClick(item)
            }
        }
    }

}