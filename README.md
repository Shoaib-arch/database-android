# database-android
Database in android 
# MainActivity code 

```
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
```

# Database Code

```
package com.example.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(database: SQLiteDatabase?) {
        var query = """
            CREATE TABLE $TABLE_NAME(
                $ID INTEGER AUTO_INCREMENT,
                $NAME TEXT NOT NULL,
                $AGE INTEGER NOT NULL,
                PRIMARY KEY ($ID)
            )
        """.trimIndent()

        database?.execSQL(query)
    }

    fun insert(id:Int, age: Int, name: String){
        val cv = ContentValues()
//        cv.put(ID, id)
        cv.put(NAME, name)
        cv.put(AGE, age)

        writableDatabase.insert(TABLE_NAME, null, cv)
    }

    fun read(): Array<Person>{
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        var data = arrayOf<Person>()
        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            do{
                data += Person(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2)
                )
            }while (cursor.moveToNext())
        }
        return data
    }

    override fun onUpgrade(database: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    val TABLE_NAME = "person"

    val ID = "_id"
    val NAME = "name"
    val AGE = "age"


}
```
