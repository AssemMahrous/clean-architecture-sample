package net.mobiquity.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.product_list_item.view.*
import net.mobiquity.R
import net.mobiquity.core.utils.loadImage
import net.mobiquity.modules.product.entities.ProductView

class ProductsAdapter(val listener: (product: ProductView, imageView: ImageView) -> Unit) :
    ListAdapter<ProductView, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val productItem = getItem(position)
        if (productItem != null) {
            (holder as ProductViewHolder).bind(productItem, listener)
        }
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(
            item: ProductView,
            listener: (item: ProductView, imageView: ImageView) -> Unit
        ) = with(itemView) {
            itemView.setOnClickListener { listener(item, image_product) }
            item.imageUrl?.let { loadImage(it, image_product) }
            text_view_product_name.text = item.name
        }

        companion object {
            fun create(parent: ViewGroup): ProductViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.product_list_item, parent, false)
                return ProductViewHolder(view)
            }
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ProductView>() {
            override fun areItemsTheSame(oldItem: ProductView, newItem: ProductView): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: ProductView, newItem: ProductView): Boolean =
                oldItem == newItem
        }
    }
}