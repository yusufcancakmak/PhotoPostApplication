package com.yusufcancakmak.photopostapplication.ui.framelays

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.yusufcancakmak.photopostapplication.ui.main.MainActivity
import com.yusufcancakmak.photopostapplication.databinding.FragmentPostBinding
import java.util.UUID

open class PostFragment : Fragment() {
    var secilenGorsel:Uri?=null
    var secilenBitmap:Bitmap?=null
    private lateinit var binding: FragmentPostBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database:FirebaseFirestore
    private lateinit var storage: FirebaseStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPostBinding.inflate(inflater,container,false)
        val view=binding.root

        auth= FirebaseAuth.getInstance()
        storage= FirebaseStorage.getInstance()
        database= FirebaseFirestore.getInstance()

        binding.btnSend.setOnClickListener {
            // share button
            // database docs
            // UUID -> UNİVERSİAL UNİQE İD
            val uuid=UUID.randomUUID()
            val gorselIsmi="${uuid}.jpg"
            val reference=storage.reference

            val gorselReference=reference.child("images").child(gorselIsmi)
            if (secilenGorsel!=null){
                gorselReference.putFile(secilenGorsel!!).addOnSuccessListener {
                    val yuklenenGorselReference=FirebaseStorage.getInstance().reference.child("images").child(gorselIsmi)
                    yuklenenGorselReference.downloadUrl.addOnSuccessListener {
                        val dowlandurl=it.toString()
                        val guncelkullaniciEmaili=auth.currentUser!!.email.toString()
                        val kullaniciyorumu=binding.etComment.text.toString()
                        val tarih=com.google.firebase.Timestamp.now()
                        //database check

                        val postHashMap= hashMapOf<String,Any>()
                        postHashMap.put("gorselurl",dowlandurl)
                        postHashMap.put("kullaniciemaili",guncelkullaniciEmaili)
                        postHashMap.put("kullaniciyorumu",kullaniciyorumu)
                        postHashMap.put("tarih",tarih)

                        database.collection("Post").add(postHashMap).addOnCompleteListener {
                            if (it.isSuccessful){
                              Toast.makeText(activity,"Finished",Toast.LENGTH_SHORT).show()
                                val intent=Intent(activity, MainActivity::class.java)
                                startActivity(intent)

                            }
                        }.addOnFailureListener {
                            Toast.makeText(activity,it.localizedMessage,Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(activity,it.localizedMessage,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.postImage.setOnClickListener {
            //selected images
            if (ContextCompat.checkSelfPermission(context as Activity,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
            }else{

                val galeriIntent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)
            }
        }
        return view
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode==1){
            if (grantResults.size>0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                // check permissons can
                val galeriIntent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==2 && resultCode==Activity.RESULT_OK && data!=null){
            secilenGorsel=data.data
            if (secilenGorsel !=null){
                if (Build.VERSION.SDK_INT>=28){
                    val source =ImageDecoder.createSource(requireActivity().contentResolver,secilenGorsel!!)
                    secilenBitmap=ImageDecoder.decodeBitmap(source)
                    binding.postImage.setImageBitmap(secilenBitmap)
                }else{
                    secilenBitmap=MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,secilenGorsel)
                    binding.postImage.setImageBitmap(secilenBitmap)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}