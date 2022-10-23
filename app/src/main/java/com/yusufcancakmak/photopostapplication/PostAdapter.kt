package com.yusufcancakmak.photopostapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yusufcancakmak.photopostapplication.data.Post
import kotlinx.android.synthetic.main.rv_row.view.*

class PostAdapter(val postList: ArrayList<Post>) :RecyclerView.Adapter<PostAdapter.PostHolder> (){
    class PostHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val infilater=LayoutInflater.from(parent.context)
        val view =infilater.inflate(R.layout.rv_row,parent,false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.itemView.row_username.text=postList[position].kullaniciemaili
        holder.itemView.row_username_comment.text=postList[position].kullaniciyorumu
        Picasso.get().load(postList[position].gorselurl).into(holder.itemView.row_image)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}