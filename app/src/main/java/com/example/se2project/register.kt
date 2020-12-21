package com.example.se2project

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vishnusivadas.advanced_httpurlconnection.PutData
import kotlinx.android.synthetic.main.layout_register.*


class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_register)

        register.setOnClickListener {

            val name = name.editText.text.toString()
            val email = email.editText.text.toString()
            val password = password.editText.text.toString()

            if (name != "" && email != "" && password != "") {

                //Start ProgressBar first (Set visibility VISIBLE)
                //Start ProgressBar first (Set visibility VISIBLE)
                val handler = Handler(Looper.getMainLooper())
                handler.post(Runnable {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    val field = arrayOfNulls<String>(3)
                    field[0] = "fullname"
                    field[1] = "email"
                    field[2] = "password"
                    //Creating array for data
                    val data = arrayOfNulls<String>(3)
                    data[0] = name
                    data[1] = email
                    data[1] = password

                    val putData = PutData(
                        "https://projects.vishnusivadas.com/AdvancedHttpURLConnection/putDataTest.php",
                        "POST",
                        field,
                        data
                    )
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            val result = putData.result

                        }
                    }
                    //End Write and Read data with URL
                })
            }
            else{
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
        }

    }
}