package com.example.cookbook

import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

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

       // base.execSQL("DROP TABLE cook")

        var sts = "Шаг 1\n" +
                "Для основы измельчите [eda], миндаль и семечки в грубую крошку, полейте растопленным маслом, немного посолите, поперчите и перемешайте. Распределите основу по 6 широким стаканам рокс или бокалам.\n" +
                "Шаг 2\n" +
                "Для чизкейка замочите желатин в холодной воде на 10 мин. Блендером смешайте козий сыр со сливочным. Сливки нагрейте, положите отжатый желатин, перемешайте до растворения, остудите, 15 мин., затем смешайте блендером с сырной массой. Распределите сырную массу по бокалам поверх основы и поставьте в холодильник застывать на 4 ч.\n" +
                "Шаг 3\n" +
                "Для мармелада разрежьте перцы вдоль пополам, удалите семена и перегородки, обжарьте перцы кожицей вверх под грилем до черных подпалин. Остудите в миске под пленкой, очистите от кожицы, нарежьте тонкой соломкой.\n" +
                "Шаг 4\n" +
                "Смешайте 120 г сахара с уксусом и 3 ст. л. воды, сварите сироп. Положите перцы в сироп, доведите до кипения, варите на слабом огне 15 мин. Смешайте оставшийся сахар с пектином, «веером» рассыпьте по поверхности перечного мармелада, энергично перемешайте, варите еще 2–3 мин., снимите с огня и полностью остудите. Разложите перечный мармелад на чизкейки, верните в холодильник на 1 ч, затем подавайте."

        var secondSts = "ПОШАГОВЫЙ РЕЦЕПТ ПРИГОТОВЛЕНИЯ\n" +
                "Шаг 1\n" +
                "Картофель и морковь вымойте щеткой и отварите в подсоленной воде до готовности. Яйца отварите отдельно, не допуская переваривания желтка. Очистите и нарежьте кубиками со стороной 5 мм. Такими же кубиками нарежьте ветчину.\n" +
                "Шаг 2\n" +
                "Смешайте все ингредиенты в салатнике, добавьте мелко нарезанный зеленый лук и укроп. Приправьте черным перцем, заправьте майонезом и поставьте в холодильник.\n" +
                "Шаг 3\n" +
                "Если соленые огурцы с крупными семенами - удалите их. Затем нарежьте кубиками такого же размера. Сложите в отдельный контейнер. Слейте жидкость с горошка, горошек переложите в отдельный контейнер.\n" +
                "Шаг 4\n" +
                "Перед подачей на стол добавьте в салат соленые огурцы и зеленый горошек. Еще раз перемешайте салат, попробуйте и при необходимости добавьте соль и черный перец."


          if (true)
        {
            base.execSQL("INSERT INTO cook VALUES ('1111111','Чизкейк из козьего сыра в бокале с мармеладом из перца','$sts')")
            base.execSQL("INSERT INTO cook VALUES ('2222222','Оливье классический','$secondSts')")
        }


        scrool.addView(child)

        add("1111111", scrool, base, "eda")
        add("2222222", scrool, base, "edas")

    }

    fun Read(start: Int = 0, first: Int = 0, second: Int = 1, str: CharArray, branch: Boolean = true, array: Array<String>, s: Int = 0, barrier: Int = 3) : Array<String> {

        if (s >= str.size-1)
            return array


        var link = ""
        var text = ""

        var First = first
        var Second = second

        var isStart = false
        var S = s
        for (i in start..str.size-1) {
            if (str[i] == '[' || isStart) {
                isStart = true
                link += str[i]
            }
             if (str[i] == ']')
             {
                 S = i + 1
                 break
             }

            if (!isStart) {
                text += str[i]
            }
            S = i
        }

        if (branch) {

            array[First] = text
            array[Second] = link

            First += 2
            Second += 2
        } else {
            First += 2
            Second += 2

            array[First] = text
            if (Second > barrier)
                return array
            array[second] = link

        }
        return Read(S, First, Second, str, false, array, S)
    }

    fun scan(Whats: Int, arr: Array<String>, key: String, scrool: LinearLayout) {

        var keys = arr[Whats]

        var sum = "eda"
        for (i in 0..keys.length - 1)
        {
            if (keys[i] != '[' && keys[i] != ']' && keys[i] != '\u0000')
                sum+=keys[i]
        }

        var image: ImageView = ImageView(this)
            image.setImageResource(resources.getIdentifier(sum, "drawable", packageName))
            scrool.addView(image)
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

    fun add(res: String, scrool: LinearLayout, db: SQLiteDatabase, imageRes: String = "img")
    {
        var noneContent: ImageButton = ImageButton(this)
        noneContent.setImageResource(resources.getIdentifier(imageRes, "drawable", packageName))
        scrool.addView(noneContent)

        noneContent.setOnClickListener()
        {
           var how = BaseReader(db, res)[1]
            var name = BaseReader(db, res)[0]

            var toNext: Intent = Intent(this, food::class.java)
            toNext.putExtra("KEY",how)
            toNext.putExtra("NAME",name)
            toNext.putExtra("IMAGE", "$imageRes")

            startActivity(toNext)
        }
    }
}
