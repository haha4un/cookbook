package com.example.cookbook

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.*

class MainActivity : AppCompatActivity() {
    var test: TextView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         test = findViewById(R.id.test)
         var scrool: LinearLayout = findViewById(R.id.scrooler)
         var child: ImageView = ImageView(this)


        var  base: SQLiteDatabase = baseContext.openOrCreateDatabase("cook.db", MODE_PRIVATE, null)
        base.execSQL("CREATE TABLE IF NOT EXISTS cook (keys TEXT NOT NULL, name TEXT NOT NULL, how TEXT NOT NULL)")

        //base.execSQL("DROP TABLE cook")

        if (savedInstanceState == null)
        {
            base.execSQL("INSERT INTO cook VALUES ('124','REpa!', 'Куипи[R.drawable.img]КуипиКуипиКуипиКуипиКуипиКуипиКуипиКуипиКуип')");
            base.execSQL("INSERT INTO cook VALUES ('OMELET','OMELETTTTTTTTTTTTTTTTTTTTTTTTTTT', 'OMELETTTTTTTTTTTTTTTTTTTTTTTTTTT')");
        }


        scrool.addView(child)
        add("124", scrool, base)
        add("OMELET", scrool, base)

    }

    fun BaseReader(db: SQLiteDatabase, key: String) : kotlin.Array<String>
    {
        var cursor: Cursor = db.rawQuery("SELECT * FROM cook", null)

        var name: String = "БЛЮДО: "
        var str: String = ""

        var arr: kotlin.Array<String> = arrayOf("empty name", "&&&")
        while(cursor.moveToNext()) {

            if (key == cursor.getString(0))
            {
                name += cursor.getString(1)
                str += "Рецепт:\n"
                str += cursor.getString(2)

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
