package com.tioeun.a201911_kotlinfinaltest.datas

import org.json.JSONObject
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class BlackList : Serializable {

    var id = 0
    var phoneNum = ""
    var title = ""
    var content = ""
    var createdAt = Calendar.getInstance()
    var writer = UserData()

    var serverTimeFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    var listDisplayTimeFormat = SimpleDateFormat("yyyy년 M월 d일\nH시 m분")

    fun getFormattedCreatedAt() : String {
        return listDisplayTimeFormat.format(this.createdAt.time)
    }

    companion object {
        fun getBlackListDataFromJson(json:JSONObject) : BlackList {
            val blackList = BlackList()

            blackList.id = json.getInt("id")
            blackList.phoneNum = json.getString("phone_num")
            blackList.title = json.getString("title")
            blackList.content = json.getString("content")
            blackList.createdAt.time = blackList.serverTimeFormat.parse(json.getString("created_at"))
            blackList.writer = UserData.getUserDataFromJson(json.getJSONObject("writer"))

            return blackList
        }
    }

}