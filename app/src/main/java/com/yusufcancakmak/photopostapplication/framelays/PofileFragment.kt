package com.yusufcancakmak.photopostapplication.framelays

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.yusufcancakmak.photopostapplication.data.Post
import com.yusufcancakmak.photopostapplication.databinding.FragmentPofileBinding

class PofileFragment : Fragment() {
    private lateinit var binding: FragmentPofileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    var postList = ArrayList<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPofileBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        return view

    }

    private fun getUserData() {
        database.collection("Post").orderBy("tarih", Query.Direction.DESCENDING)
            .addSnapshotListener { snopshot, exception ->
                if (exception != null) {
                    Toast.makeText(activity, exception.localizedMessage, Toast.LENGTH_SHORT).show()
                } else {
                    if (snopshot != null) {
                        if (snopshot.isEmpty == false) {
                            val documents = snopshot.documents
                            postList.clear()
                            for (document in documents) {
                                val useremail = document.get("kullaniciemaili") as String
                                val usercomment = document.get("kullaniciyorumu") as String
                                val ımageurl = document.get("gorselurl") as String

                                val dowlandPost = Post(useremail, usercomment, ımageurl)
                                postList.add(dowlandPost)
                            }

                        }
                    }
                }
            }
    }
}

