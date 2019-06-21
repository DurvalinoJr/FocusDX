package br.com.dxsoftware.focusdx.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.dxsoftware.focusdx.R
import br.com.dxsoftware.focusdx.business.UserBusiness
import br.com.dxsoftware.focusdx.repository.UserRepository
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var mUserBusiness : UserBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Events
        setListeners()
        UserRepository.getInstance(this)

        // Intanciate class variables
        mUserBusiness = UserBusiness(this)
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.buttonSave -> handleSave()
        }
    }

    private fun setListeners(){
        buttonSave.setOnClickListener(this)
    }

    private fun handleSave(){
        val name = editName.text.toString()
        val email = editEmail.text.toString()
        val password = editPassword.text.toString()

        mUserBusiness.insert(name, email, password)
    }
}