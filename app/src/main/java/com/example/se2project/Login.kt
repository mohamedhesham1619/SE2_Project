package com.example.se2project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.vishnusivadas.advanced_httpurlconnection.PutData

class Login : AppCompatActivity() {

    private lateinit var usernameEditText: TextInputLayout
    private lateinit var passwordEditText: TextInputLayout
    private lateinit var login: Button
    private lateinit var register: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login)

        usernameEditText = findViewById(R.id.loginActivity_username)
        passwordEditText = findViewById(R.id.loginActivity_password)
        login = findViewById(R.id.loginActivity_login)
        register = findViewById(R.id.loginActivity_register)

        login.setOnClickListener {

            val username: String = usernameEditText.editText?.text.toString()
            val password: String = passwordEditText.editText?.text.toString()



            if (username != "" && password != "") {

                //Start ProgressBar first (Set visibility VISIBLE)
                //Start ProgressBar first (Set visibility VISIBLE)
                val handler = Handler(Looper.getMainLooper())
                handler.post(Runnable {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    val field = arrayOfNulls<String>(2)
                    field[0] = "username"
                    field[1] = "password"
                    //Creating array for data
                    val data = arrayOfNulls<String>(2)
                    data[0] = username
                    data[1] = password

                    val putData = PutData(
                        "http://192.168.0.2/Login_Register/login.php",
                        "POST",
                        field,
                        data
                    )
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            val result = putData.result
                            if (result == "Login Success") {

                                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, Home::class.java)
                                startActivity(intent)
                                finish()

                            } else {
                                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                    //End Write and Read data with URL
                })
            } else {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
        }
        register.setOnClickListener {
            val intent = Intent(this, com.example.se2project.register::class.java)
            startActivity(intent)
            finish()
        }
    }
}