package com.tioeun.a201911_kotlinfinaltest

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tioeun.a201911_kotlinfinaltest.utils.ContextUtil
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
                ContextUtil.setSaveIdChecked(mContext, true)
            } else {
//                해제되는 상황
                ContextUtil.setSaveIdChecked(mContext, false)
            }
        }
    }

    override fun setValues() {
        saveIdCheckbox.isChecked = ContextUtil.getSaveIdChecked(mContext)
    }


}
