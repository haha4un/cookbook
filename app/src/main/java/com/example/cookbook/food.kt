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



        var howTxt: TextView = findViewById(R.id.how)
        var nameTxt: TextView = findViewById(R.id.name)

        howTxt.text = res.toString()
        nameTxt.text = name.toString()

        var imageView: ImageButton = ImageButton(this)
        scroller.addView(imageView)
        imageView.setImageResource(R.drawable.img)
    }
//    fun AddImages(array: CharArray, scrool: LinearLayout)
//    {
//        var count = 0
//        var toIm = 0
//        var Img: String = ""
//
//        var Imgs = Array(2, {""})
//        var imgCount = 0
//
//        while (count < array.size)
//        {
//            if (array[count] == '[')
//            {
//                toIm = count+1
//                while (true)
//                {
//                    if (array[toIm] == ']')
//                    {
//                        imgCount++
//                        break
//                    }
//                    Img += array[toIm]
//                    toIm++
//                }
//                Imgs[imgCount] = Img
//            }
//            if (imgCount == 10)
//                break
//            count++
//        }
//    } //I don't know why I made It _/-(/_/)-\_
}