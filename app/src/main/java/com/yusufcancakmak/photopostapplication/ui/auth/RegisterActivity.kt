package com.yusufcancakmak.photopostapplication.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.yusufcancakmak.photopostapplication.ui.main.MainActivity
import com.yusufcancakmak.photopostapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirebase()
        checkActiveUser()
        initListeners()
    }

    private fun initFirebase() {
        auth = FirebaseAuth.getInstance()
    }

    private fun checkActiveUser() {
        val activeUser = auth.currentUser
        activeUser?.let {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}
