package br.com.dxsoftware.focusdx.business

import android.content.Context
import br.com.dxsoftware.focusdx.R
import br.com.dxsoftware.focusdx.constants.FocusConstants
import br.com.dxsoftware.focusdx.repository.UserRepository
import br.com.dxsoftware.focusdx.util.SecurityPreferences
import br.com.dxsoftware.focusdx.util.ValidationException

class UserBusiness (val context: Context){

    private val mUserRepository : UserRepository = UserRepository.getInstance(context)
    private val mSecurityPreferences: SecurityPreferences = SecurityPreferences(context)

    fun insert(name: String, email: String, password: String){
        try {
            if (name == "" || email == "" || password == "")
                throw  ValidationException(context.getString(R.string.stringFill_All_Fields))
            
            if (mUserRepository.isEmailExistent(email))
                throw  ValidationException(context.getString(R.string.stringEmailAlreadyRegistered))

            val userId = mUserRepository.insert(name, email, password)

            mSecurityPreferences.storeString(FocusConstants.KEY.USER_ID, userId.toString())
            mSecurityPreferences.storeString(FocusConstants.KEY.USER_NAME, name)
            mSecurityPreferences.storeString(FocusConstants.KEY.USER_EMAIL, email)
        }catch(e: Exception){
            throw e
        }
    }


    fun login (email: String, password: String) : Boolean {

        val user = mUserRepository.get(email, password)

        return if (user != null) {
            mSecurityPreferences.storeString(FocusConstants.KEY.USER_ID, user.id.toString())
            mSecurityPreferences.storeString(FocusConstants.KEY.USER_NAME, user.name)
            mSecurityPreferences.storeString(FocusConstants.KEY.USER_EMAIL, user.email)
            true
        } else {
            false
        }
    }
}