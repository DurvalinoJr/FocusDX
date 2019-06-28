package br.com.dxsoftware.focusdx.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.dxsoftware.focusdx.R
import br.com.dxsoftware.focusdx.business.UserBusiness
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserBusiness: UserBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mUserBusiness = UserBusiness(this)

        setListeners()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.buttonLogin -> handleLogin()
        }
    }

    private fun setListeners(){
        buttonLogin.setOnClickListener(this)
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
