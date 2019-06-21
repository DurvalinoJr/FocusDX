package br.com.dxsoftware.focusdx.business

import android.content.Context
import br.com.dxsoftware.focusdx.repository.UserRepository

class UserBusiness (val context: Context){

    private val mUserRepository : UserRepository = UserRepository.getInstance(context)

    fun insert(name: String, email: String, password: String){
        val userId = mUserRepository.insert(name, email, password)
        val str = ""
    }


}