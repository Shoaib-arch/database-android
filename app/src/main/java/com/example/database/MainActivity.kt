package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val dbHelper: DBHelper = DBHelper(this, "users", null, 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper.insert(1, 21,"Shoaib")
        dbHelper.insert(2, 22,"Shoaib Raees")
        dbHelper.insert(3, 20,"Shoaib Pasha")
        dbHelper.insert(4, 23,"Shoaib Raja Sultan")
        dbHelper.insert(5, 19,"Razia Sultan")

        val persons = dbHelper.read()
        var text = ""
        for (p in persons){
            text += "${p.id} ${p.name} ${p.age} \n"
        }
        findViewById<TextView>(R.id.text).text = text
    }
}