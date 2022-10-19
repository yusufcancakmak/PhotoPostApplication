package com.yusufcancakmak.photopostapplication.framelays

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.yusufcancakmak.photopostapplication.R
import com.yusufcancakmak.photopostapplication.RegisterActivity
import com.yusufcancakmak.photopostapplication.databinding.ActivityRegisterBinding
import com.yusufcancakmak.photopostapplication.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var mcontext:Context
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentSettingsBinding.inflate(inflater,container,false)
        val view=binding.root

        auth=FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            auth.signOut()
            val intent=Intent(this.activity,RegisterActivity::class.java)
            startActivity(intent)







        }

        return view


    }

}