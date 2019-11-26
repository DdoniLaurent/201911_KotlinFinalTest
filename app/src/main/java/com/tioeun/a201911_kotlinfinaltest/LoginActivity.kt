package com.tioeun.a201911_kotlinfinaltest

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tioeun.a201911_kotlinfinaltest.datas.UserData
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

        loginBtn.setOnClickListener {
            val userId = idEdt.text.toString()
            val userPw = pwEdt.text.toString()

            ServerUtil.postRequestLogin(mContext, userId, userPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    Log.d("서버응답", json.toString())

                    val code = json.getInt("code")
                    if(code == 200) {
                        var data = json.getJSONObject("data")
                        var user = data.getJSONObject("user")
                        var token = data.getString("token")
                        ContextUtil.setUserToken(mContext, token)

                        val userData = UserData.getUserDataFromJson(user)

                        val intent = Intent(mContext, BoardActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else if(code == 400) {
                        runOnUiThread {
                            Toast.makeText(mContext, "로그인 실패!!!", Toast.LENGTH_SHORT).show()
                        }
//                        500 => INTERVAL SERVER ERROR => 서버 내부 에러 => 서버개발자의 실수
//                        404 Not found => 없는 주소에 요청
//                        403 => 권한이 없는 요청
                    } else {
                        runOnUiThread {
                            Toast.makeText(mContext, "서버에 문제가 있습니다!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })

        }

        saveIdCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->


            ContextUtil.setSaveIdChecked(mContext, isChecked)
        }

    }

    override fun setValues() {

        saveIdCheckBox.isChecked = ContextUtil.getSaveIdChecked(mContext)
    }

}
