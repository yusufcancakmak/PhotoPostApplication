package com.yusufcancakmak.photopostapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.yusufcancakmak.photopostapplication.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.btnSignUpLogin.setOnClickListener {
            val intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSignUpSign.setOnClickListener {
            val username=binding.etUsername.text.toString()
            val email=binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()
            val confirmPassword=binding.etConfirmPassword.text.toString()


            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(applicationContext,"Accound sussecfull", Toast.LENGTH_SHORT).show()
                }


            }.addOnFailureListener {exeception->

                Toast.makeText(applicationContext,exeception.message, Toast.LENGTH_SHORT).show()
            }

            }




        }

    }
