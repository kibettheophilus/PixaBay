package dev.kibet.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.kibet.domain.models.Image
import dev.kibet.presentation.databinding.ImageItemBinding

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {
    class ImageViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ImageItemBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int): Unit = with(holder.binding) {
        val image = differ.currentList[position]
        val context = holder.itemView.context
        imageOwner.text = image.user
        Glide.with(context).load(image.webformatURL).centerCrop().into(imageView)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(image)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((Image) -> Unit)? = null

    fun setOnItemClickListener(listener: (Image) -> Unit) {
        this.onItemClickListener = listener
    }
}
