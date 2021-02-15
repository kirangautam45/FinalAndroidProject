package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.finalproject.db.UserDB
import com.example.finalproject.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationActivity : AppCompatActivity() {
    private lateinit var Fname : EditText
    private lateinit var Lname: EditText
    private lateinit var Username: EditText
    private lateinit var Address: EditText
    private lateinit var Phone: EditText
    private lateinit var Password: EditText
    private lateinit var ConfirmPassword: EditText
    private lateinit var btnAdduser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        Fname=findViewById(R.id.Fname)
        Lname=findViewById(R.id.Lname)
        Username=findViewById(R.id.Username)
        Address=findViewById(R.id.Address)
        Phone=findViewById(R.id.Phone)
        Password=findViewById(R.id.Password)
        ConfirmPassword=findViewById(R.id.ConfirmPassword)
        btnAdduser=findViewById(R.id.btnAdduser)

        btnAdduser.setOnClickListener {
            val fname= Fname.text.toString()
            val lname=Lname.text.toString()
            val address = Address.text.toString()
            val phone = Phone.text.toString()
            val username = Username.text.toString()
            val password =Password.text.toString()
            val confirmpassword = ConfirmPassword.text.toString()

            if(password != confirmpassword){
                Password.error= "password does not match"
                Password.requestFocus()
                return@setOnClickListener }
            else {
                val user= User(fname,lname,address,phone,username,password)
                CoroutineScope(Dispatchers.IO).launch { UserDB
                    .getInstance(this@RegistrationActivity)
                    .getUserDAO()
                    .registerUser(user )
                    //switch to main thread
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@RegistrationActivity,
                            "user saved", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}