package com.example.cookbook

import android.content.Intent
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
import java.lang.reflect.Array
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
        base.execSQL("CREATE TABLE IF NOT EXISTS cooks (key, name, what)")
        base.execSQL("INSERT INTO cooks VALUES ('Кулябяка', 'КуипиКуипиКуипиКуипиКуипиКуипиКуипиКуипиКуипиКуип')");

        scrool.addView(child)
        add("Кулябяка", scrool, base)

    }

    fun BaseReader(db: SQLiteDatabase, key: String) : kotlin.Array<String>
    {
        var cursor: Cursor = db.rawQuery("SELECT * FROM cooks", null)

        var name: String = "БЛЮДО: "
        var str: String = ""

        var arr: kotlin.Array<String> = arrayOf("empty name", "&&&")
        while(cursor.moveToNext()) {

            if (key == cursor.getString(0))
            {
                name += cursor.getString(0)
                str += "Рецепт:\n"
                str += cursor.getString(1)

                arr = arrayOf(name, str)
                return arr
            }

        }

        cursor.close()
        return arr;
    }

    fun add(res: String, scrool: LinearLayout, db: SQLiteDatabase)
    {
        var noneContent: ImageButton = ImageButton(this)
        noneContent.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.img))
        scrool.addView(noneContent)

        noneContent.setOnClickListener()
        {
           var how = BaseReader(db, res)[1]
            var name = BaseReader(db, res)[0]

            var toNext: Intent = Intent(this, food::class.java)
            toNext.putExtra("KEY",how)
            toNext.putExtra("NAME",name)
            startActivity(toNext)
        }
    }
}

