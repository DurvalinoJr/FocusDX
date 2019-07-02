package br.com.dxsoftware.focusdx.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.dxsoftware.focusdx.R
import br.com.dxsoftware.focusdx.business.UserBusiness
import br.com.dxsoftware.focusdx.constants.FocusConstants
import br.com.dxsoftware.focusdx.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserBusiness: UserBusiness
    private lateinit var mSecurityPreferences: SecurityPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mUserBusiness = UserBusiness(this)
        mSecurityPreferences = SecurityPreferences(this)

        setListeners()
        verifyLoggedUser()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.buttonLogin -> handleLogin()
        }
    }

    private fun setListeners(){
        buttonLogin.setOnClickListener(this)
    }

    private fun verifyLoggedUser(){
        val userId = mSecurityPreferences.getStoredString(FocusConstants.KEY.USER_ID)
        val userName = mSecurityPreferences.getStoredString(FocusConstants.KEY.USER_NAME)

        if (userId != "" && userName != "" ){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    private fun handleLogin(){
        val email = editEmail.text.toString()
        val password = editPassword.text.toString()

        if(mUserBusiness.login(email, password)){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this, getString(R.string.stringUserPasswordIncorrect), Toast.LENGTH_LONG).show()
        }
    }
}
