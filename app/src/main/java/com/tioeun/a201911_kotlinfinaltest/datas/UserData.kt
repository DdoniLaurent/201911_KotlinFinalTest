package com.tioeun.a201911_kotlinfinaltest.datas

import org.json.JSONObject
import java.io.Serializable

class UserData : Serializable {

    var loginId = ""

    var name = ""

    var phone = ""

    var cetegory = Category()

    companion object {
        fun getUserDataFromJson(json: JSONObject) : UserData {
            var user = UserData()
            user.loginId = json.getString("login_id")
            user.name = json.getString("name")
            user.phone = json.getString("phone")
            return user
        }
    }
}