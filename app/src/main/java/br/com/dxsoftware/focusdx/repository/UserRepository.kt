package br.com.dxsoftware.focusdx.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import br.com.dxsoftware.focusdx.constants.DataBaseConstants
import br.com.dxsoftware.focusdx.entities.UserEntity

class UserRepository private constructor(context: Context){

    companion object{
        private var INSTANCE: UserRepository? = null

        fun getInstance(context: Context): UserRepository{
            if (INSTANCE == null)
                INSTANCE = UserRepository(context)

            return INSTANCE as UserRepository
        }
    }

    private var mFocusDataBaseHelper : FocusDataBaseHelper = FocusDataBaseHelper(context)

    fun insert(name: String, email: String, password: String): Int{
        val db = mFocusDataBaseHelper.writableDatabase

        val insertValues = ContentValues()
        insertValues.put(DataBaseConstants.USER.COLUMNS.NAME, name)
        insertValues.put(DataBaseConstants.USER.COLUMNS.EMAIL, email)
        insertValues.put(DataBaseConstants.USER.COLUMNS.PASSWORD, password)

        return db.insert(DataBaseConstants.USER.TABLE_NAME, null, insertValues).toInt()
    }

    fun isEmailExistent(email: String) : Boolean{
        val ret: Boolean
        try {
            val cursor: Cursor
            val db = mFocusDataBaseHelper.readableDatabase

            val projection = arrayOf(DataBaseConstants.USER.COLUMNS.ID)
            val selection = "${DataBaseConstants.USER.COLUMNS.EMAIL} = ?"
            val selectionArgs = arrayOf(email)

            cursor = db.query(DataBaseConstants.USER.TABLE_NAME, projection, selection, selectionArgs, null, null, null)

            ret = cursor.count > 0

            cursor.close()
        } catch (e: Exception){
            throw e
        }

        return ret
    }

    fun get(email: String, password: String){

        var userEntity: UserEntity? = null // TODO: Finalizar a implementação deste método

        try {
            val cursor: Cursor
            val db = mFocusDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.USER.COLUMNS.ID,
                DataBaseConstants.USER.COLUMNS.NAME,
                DataBaseConstants.USER.COLUMNS.EMAIL,
                DataBaseConstants.USER.COLUMNS.PASSWORD)

            val selection = "${DataBaseConstants.USER.COLUMNS.EMAIL} = ? AND ${DataBaseConstants.USER.COLUMNS.PASSWORD} = ?"
            val selectionArgs = arrayOf(email, password)

            cursor = db.query(DataBaseConstants.USER.TABLE_NAME, projection, selection, selectionArgs, null, null, null)

            if (cursor.count > 0)


            cursor.close()

        } catch (e: Exception){
            throw e
        }

    }


}