package com.example.se2project

import android.util.Log
import java.sql.DriverManager
import java.sql.SQLException

class Test {
    fun connection() {
        try {
            val c = DriverManager.getConnection(
                "jdbc:mysql://localhost/users?serverTimezone=UTC",
                "mh",
                "123"
            )
            Log.d("testt", "succeeded ")

        }
        catch (e: SQLException){
            println(e)
        }

    }
}