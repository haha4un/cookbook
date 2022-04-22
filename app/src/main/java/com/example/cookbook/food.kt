package com.example.cookbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class food : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        var res = intent.getSerializableExtra("KEY")
        var name = intent.getSerializableExtra("NAME")

        var howTxt: TextView = findViewById(R.id.how)
        var nameTxt: TextView = findViewById(R.id.name)

        howTxt.text = res.toString()
        nameTxt.text = name.toString()
    }
}