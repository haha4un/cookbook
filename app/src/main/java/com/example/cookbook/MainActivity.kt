package com.example.cookbook

import android.content.res.Resources
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import java.util.*

class MainActivity : AppCompatActivity() {

    var test: TextView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         test = findViewById(R.id.test)
         var scrool: LinearLayout = findViewById(R.id.scrooler)
         var child: ImageView = ImageView(this)

         var str = getFilesDir().getPath() + "cooks.sql"

        var base: SQLiteDatabase = baseContext.openOrCreateDatabase("cook.db", MODE_PRIVATE, null)
        base.execSQL("CREATE TABLE IF NOT EXISTS cooks (name, what)")
        base.execSQL("INSERT INTO cooks VALUES ('Кулябяка', 'Куипи')")
        base.execSQL("INSERT INTO cooks VALUES ('Кулябяка', 'Куипи')")
        base.execSQL("INSERT INTO cooks VALUES ('Кулябяка', 'Куипи')")


        scrool.addView(child)
        add("f", scrool)
        add("f", scrool)
        add("f", scrool)
        add("f", scrool)
        add("f", scrool)
        add("f", scrool)
        add("f", scrool)
        add("f", scrool)
        add("f", scrool)
        add("f", scrool)
        add("f", scrool)

        str = BaseReader(base)
    }

    fun BaseReader(db: SQLiteDatabase) : String
    {
        var cursor: Cursor = db.rawQuery("SELECT * FROM cooks", null)

        var str: String = "БЛЮДО: "
        while(cursor.moveToNext())
        {
             str += cursor.getString(0)
            str += " Рецепт: "
             str+= cursor.getString(1)
        }

        cursor.close()
        return str;
    }

    fun add(res: String, scrool: LinearLayout, db: SQLiteDatabase)
    {
        var noneContent: ImageButton = ImageButton(this)
        noneContent.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.img))
        scrool.addView(noneContent)

        noneContent.setOnClickListener()
        {
            BaseReader(db)
        }
    }
}