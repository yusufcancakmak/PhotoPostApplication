package com.yusufcancakmak.photopostapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.yusufcancakmak.photopostapplication.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()

        val aktifkullanici=auth.currentUser
        if (aktifkullanici!=null){
            val intent =Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }



        binding.btnLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(binding.etEmail.text.toString(),binding.etPassword.text.toString()).addOnCompleteListener {

                if (it.isSuccessful){
                    val aktifkullanici =auth.currentUser?.email.toString()
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        binding.btnSignUp.setOnClickListener {
            val intent=Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }



    }
    }
