package com.tioeun.a201911_kotlinfinaltest

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

//    private SharedPreferences appData;
    var appData:SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        saveIdCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
//                체크박스가 체크되는 상황
            } else {
//                해제되는 상황

            }
        }
    }

    override fun setValues() {

    }


}
