package br.com.dxsoftware.focusdx.repository

import android.content.ContentValues
import android.content.Context
import android.provider.ContactsContract
import br.com.dxsoftware.focusdx.constants.DataBaseConstants

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

}