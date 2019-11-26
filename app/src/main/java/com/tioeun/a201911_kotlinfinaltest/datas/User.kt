package com.tioeun.a201911_kotlinfinaltest.datas

import org.json.JSONObject

class User {

    var loginId = ""

    var password = ""

    companion object {
        fun getCategoryFromJson(json: JSONObject) : User {
            var user = User()
            user.loginId = json.getString("login_id")
            user.password = json.getString("password ")
            return user
        }
    }
}