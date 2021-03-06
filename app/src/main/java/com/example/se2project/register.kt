package com.example.se2project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.vishnusivadas.advanced_httpurlconnection.PutData
import kotlinx.android.synthetic.main.layout_register.*


class register : AppCompatActivity() {

    private lateinit var fullnameEditText: TextInputLayout
    private lateinit var usernameEditText: TextInputLayout
    private lateinit var emailEditText: TextInputLayout
    private lateinit var passwordEditText: TextInputLayout
    private lateinit var register: Button
    private lateinit var login: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_register)

        fullnameEditText = findViewById(R.id.registerActivity_fullname)
        usernameEditText = findViewById(R.id.registerActivity_username)
        emailEditText = findViewById(R.id.registerActivity_email)
        passwordEditText = findViewById(R.id.registerActivity_password)
        register = findViewById(R.id.registerActivity_register)
        login = findViewById(R.id.registerActivity_login)

        register.setOnClickListener {

            val fullname: String = fullnameEditText.editText?.text.toString()
            val username: String = usernameEditText.editText?.text.toString()
            val email: String = emailEditText.editText?.text.toString()
            val password: String = passwordEditText.editText?.text.toString()



            if (fullname != "" && username != "" && email != "" && password != "") {

                //Start ProgressBar first (Set visibility VISIBLE)
                //Start ProgressBar first (Set visibility VISIBLE)
                val handler = Handler(Looper.getMainLooper())
                handler.post(Runnable {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    val field = arrayOfNulls<String>(4)
                    field[0] = "fullname"
                    field[1] = "username"
                    field[2] = "password"
                    field[3] = "email"
                    //Creating array for data
                    val data = arrayOfNulls<String>(4)
                    data[0] = fullname
                    data[1] = username
                    data[2] = email
                    data[3] = password

                    val putData = PutData(
                        "http://192.168.0.2/Login_Register/signup.php",
                        "POST",
                        field,
                        data
                    )
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            val result = putData.result
                            if (result == "Sign Up Success") {

                                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, Login::class.java)
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

        login.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}