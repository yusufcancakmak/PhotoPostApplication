package com.yusufcancakmak.photopostapplication.ui.framelays

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.yusufcancakmak.photopostapplication.data.Post
import com.yusufcancakmak.photopostapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var database:FirebaseFirestore
    private lateinit var adapter: PostAdapter
    var postList=ArrayList<Post>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding=FragmentHomeBinding.inflate(inflater,container,false)
        val view=binding.root

        auth= FirebaseAuth.getInstance()
        database= FirebaseFirestore.getInstance()
        getData()
        var layoutManager=LinearLayoutManager(activity)
        binding.rowRv.layoutManager=layoutManager
        adapter= PostAdapter(postList)
        binding.rowRv.adapter=adapter
        return view
    }
    private fun getData(){
        database.collection("Post").orderBy("tarih",Query.Direction.DESCENDING).addSnapshotListener{ snapshot,exception ->
            if (exception!=null){
                Toast.makeText(activity,exception.localizedMessage,Toast.LENGTH_SHORT).show()
            }else{
                if (snapshot!=null){
                    if (snapshot.isEmpty==false){
                        val documents=snapshot.documents
                        postList.clear()
                        for (document in documents){
                            val useremail=document.get("kullaniciemaili") as String
                            val usercomment=document.get("kullaniciyorumu") as String
                            val ımageurl =document.get("gorselurl") as String

                            val dowlandPost = Post(useremail,usercomment,ımageurl)
                            postList.add(dowlandPost)
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

}