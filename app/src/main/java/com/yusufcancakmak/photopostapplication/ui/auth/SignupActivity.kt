package com.yusufcancakmak.photopostapplication.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.yusufcancakmak.photopostapplication.R
import com.yusufcancakmak.photopostapplication.databinding.ActivitySignupBinding
import com.yusufcancakmak.photopostapplication.extensions.showMessage
import com.yusufcancakmak.photopostapplication.extensions.startActivity

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirebase()
        initListeners()
    }

    private fun initFirebase() {
        auth = FirebaseAuth.getInstance()
    }

    private fun initListeners() {
        binding.btnSignUpLogin.setOnClickListener {
            startActivity(RegisterActivity::class.java, null)
        }

        binding.btnSignUpSign.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful)
                    showMessage(getString(R.string.signup_success_msg))
            }.addOnFailureListener { exception ->
                showMessage(exception.message ?: getString(R.string.general_error_msg))
            }

        }
    }

}
