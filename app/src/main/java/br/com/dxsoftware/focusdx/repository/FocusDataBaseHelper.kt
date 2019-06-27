package br.com.dxsoftware.focusdx.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.dxsoftware.focusdx.constants.DataBaseConstants

class FocusDataBaseHelper (context: Context) :SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION: Int = 1
        private val DATABASE_NAME: String = "FocusDX.db"
    }

    private val  createTableUser: String
        get() = """ CREATE TABLE ${DataBaseConstants.USER.TABLE_NAME} (
                ${DataBaseConstants.USER.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${DataBaseConstants.USER.COLUMNS.NAME} TEXT,
                ${DataBaseConstants.USER.COLUMNS.EMAIL} TEXT,
                ${DataBaseConstants.USER.COLUMNS.PASSWORD} TEXT); """

    private val deleteTableUser = "DROP TABLE IF EXISTS ${DataBaseConstants.USER.TABLE_NAME};"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createTableUser)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Remove
        db.execSQL(deleteTableUser)


        //Create
        db.execSQL(createTableUser)
    }

}
