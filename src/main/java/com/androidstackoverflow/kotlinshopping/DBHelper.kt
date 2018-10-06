package com.androidstackoverflow.kotlinshopping

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper(val context: Context): SQLiteOpenHelper(context,DBHelper.DB_NAME,null,DBHelper.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_TABLE_DEPT = "CREATE TABLE ${DEPT_TABLE} ($colidD INTEGER PRIMARY KEY, $colDept TEXT);"
        val CREATE_TABLE_ITEM = "CREATE TABLE ${ITEM_TABLE} ($colidI INTEGER PRIMARY KEY, $colItem TEXT);"
        db!!.execSQL(CREATE_TABLE_DEPT)
        db.execSQL(CREATE_TABLE_ITEM)

        Toast.makeText(context, " database is created", Toast.LENGTH_LONG).show()

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE_DEPT = "DROP TABLE IF EXISTS $DEPT_TABLE"
        val DROP_TABLE_ITEM = "DROP TABLE IF EXISTS $ITEM_TABLE"
        db!!.execSQL(DROP_TABLE_DEPT)
        db.execSQL(DROP_TABLE_ITEM)

        Toast.makeText(context, "tables droped new db created", Toast.LENGTH_LONG).show()

        onCreate(db)
    }

    fun queryDEPT(): List<ModelDept> {
        val db = this.writableDatabase
        val deptList = ArrayList<ModelDept>()
        val selectQuery = "SELECT  * FROM ${DEPT_TABLE}"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val contact = ModelDept()
                    contact.idD = Integer.parseInt(cursor.getString(cursor.getColumnIndex(colidD)))
                    contact.dept = cursor.getString(cursor.getColumnIndex(colDept))
                    deptList.add(contact)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return deptList
    }

    fun queryITEM(): List<ModelItem> {
        val db = this.writableDatabase
        val itemList = ArrayList<ModelItem>()
        val selectQuery = "SELECT  * FROM ${ITEM_TABLE}"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val contact = ModelItem()
                    contact.idI = Integer.parseInt(cursor.getString(cursor.getColumnIndex(colidI)))
                    contact.item = cursor.getString(cursor.getColumnIndex(colItem))
                    itemList.add(contact)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return itemList
    }

    fun insertDEPT(values: ContentValues): Long {
        val db = this.writableDatabase
        val idD = db.insert(DEPT_TABLE, null, values)
        return idD
    }

    fun updateDEPT(values: ContentValues,selection: String,selectionargs: Array<String>):Int{
        val db = this.writableDatabase
        val dept = db.update(DEPT_TABLE,values,selection,selectionargs)
        return dept
    }

    fun insertITEM(values: ContentValues): Long {
        val db = this.writableDatabase
        val idI = db.insert(ITEM_TABLE, null, values)
        return idI
    }

    fun updateITEM(values: ContentValues, selection: String, selectionargs: Array<String>): Int {
        val db = this.writableDatabase
        val count = db.update(ITEM_TABLE, values, selection, selectionargs)
        return count
    }

    fun deleteDEPT(productname: String): Boolean {
        var result = false
        val query = "SELECT * FROM $DEPT_TABLE WHERE $colDept= \"$productname\""
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(DEPT_TABLE, "$colidD = ?", arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result
    }

    fun deleteITEM(productname: String): Boolean {
        var result = false
        val query = "SELECT * FROM $ITEM_TABLE WHERE $colItem= \"$productname\""
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(ITEM_TABLE, "$colidI = ?", arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result
    }

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "Grocery.db"
        private val DEPT_TABLE = "Deptatment"
        private val colidD = "idD"
        private val colDept = "Dept"
        private val ITEM_TABLE = "GroceryItems"
        private val colidI = "idI"
        private val colItem = "Item"
    }
}
