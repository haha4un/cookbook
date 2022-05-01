package com.example.cookbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class food : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        var scroller: LinearLayout = findViewById(R.id.scrooler)

        var res = intent.getSerializableExtra("KEY")
        var name = intent.getSerializableExtra("NAME")
        var image = intent.getStringExtra("IMAGE")

        val mDrawableName = image
        val resID = resources.getIdentifier(mDrawableName, "drawable", packageName)

        var howTxt: TextView = findViewById(R.id.how)

        howTxt.text =name.toString() +"\n\n"+ res.toString()

        var imageView: ImageButton = ImageButton(this)
        imageView.setImageResource(resID)
        scroller.addView(imageView)
    }
}