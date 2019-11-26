package com.tioeun.a201911_kotlinfinaltest

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tioeun.a201911_kotlinfinaltest.utils.ContextUtil
import com.tioeun.a201911_kotlinfinaltest.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

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

        loginBtn.setOnClickListener {

            ServerUtil.postRequestLogin(mContext, idEdt.text.toString(), pwEdt.text.toString(), object : ServerUtil.JonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    Log.d("로그인 응답", json.toString())

                    val code = json.getInt("code")

                    if(code == 200) {
                        val data = json.getJSONObject("data")
                        val token = data.getString("token")

                        ContextUtil.setUserToken(mContext, token)

                        val intent = Intent(mContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {

                    }
                }

            })

            if(saveIdCheckbox.isChecked){

                ContextUtil.setUserId(mContext, idEdt.text.toString())

                Toast.makeText(mContext, "아이디를 저장했습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(mContext, "아이디를 저장 X", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setValues() {

        //        저장되어 있는 아이디가 뭔지?
        var savedId = ContextUtil.getUserId(mContext)
        idEdt.setText(savedId)

        saveIdCheckbox.isChecked = ContextUtil.getSaveIdChecked(mContext)
    }


}
