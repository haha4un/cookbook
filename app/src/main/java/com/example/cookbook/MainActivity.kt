package com.example.cookbook

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var test: TextView = findViewById(R.id.test)
        var scrool: LinearLayout = findViewById(R.id.scrooler)
        var child: TextView = TextView(this)
        var image: ImageView = ImageView(this)


        image.setImageURI(Uri.parse("https://klike.net/uploads/posts/2019-06/1559545617_2.jpg"))

        var str = getFilesDir().getPath() + "cooks.sql"

        var base: SQLiteDatabase = baseContext.openOrCreateDatabase("cook.db", MODE_PRIVATE, null)

        child.textSize = 35f
        child.text = BaseReader(base)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
        add(child.text.toString(), scrool)
    }

    fun BaseReader(db: SQLiteDatabase) : String
    {
        var cursor: Cursor = db.rawQuery("SELECT * FROM cook", null)

        var str: String = "БЛЮДО: "
        if(cursor.moveToFirst())
        {
             str += cursor.getString(0)
            str += " Рецепт: "
             str+= cursor.getString(1)
        }
        return str;
    }

    fun add(str: String, scrool: LinearLayout)
    {
        var noneContent: ImageView = ImageView(this)
//        noneContent.text = str
        scrool.addView(noneContent)
    }
}