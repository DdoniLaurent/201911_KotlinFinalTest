package com.tioeun.a201911_kotlinfinaltest.datas

import org.json.JSONObject
import java.io.Serializable

class Category : Serializable {

    var id = 0
    var title = ""
    var color = ""

    companion object {
        fun getCategoryFromJson(jsonObject: JSONObject) : Category {
            var category = Category()
            category.id = jsonObject.getInt("id")
            category.title = jsonObject.getString("title")
            category.color = jsonObject.getString("color")
            return category
        }
    }
}