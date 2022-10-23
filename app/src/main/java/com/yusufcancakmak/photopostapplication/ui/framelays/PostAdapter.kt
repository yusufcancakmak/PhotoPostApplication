package com.yusufcancakmak.photopostapplication.ui.framelays

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusufcancakmak.photopostapplication.data.Post
import com.yusufcancakmak.photopostapplication.databinding.RvRowBinding
import com.yusufcancakmak.photopostapplication.extensions.loadImage

class PostAdapter(val postList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.PostHolder>() {
    class PostHolder(private val binding: RvRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.rowUsername.text = post.kullaniciemaili
            binding.rowUsernameComment.text = post.kullaniciyorumu
            binding.rowImage.loadImage(post.gorselurl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = RvRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}