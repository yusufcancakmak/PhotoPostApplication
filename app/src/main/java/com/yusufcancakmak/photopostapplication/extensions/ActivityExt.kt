package com.yusufcancakmak.photopostapplication.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

fun Activity.showMessage(message:String) {
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}

fun <T> Activity.startActivity(targetClass: Class<T>,bundle:Bundle?) {
    val intent = Intent(this,targetClass)
    startActivity(intent,bundle)
}