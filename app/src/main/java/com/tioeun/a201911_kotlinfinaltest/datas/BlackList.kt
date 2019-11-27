package com.tioeun.a201911_kotlinfinaltest.datas

import java.io.Serializable
import java.util.*

class BlackList : Serializable {

    var id = 0
    var phoneNum = ""
    var title = ""
    var content = ""
    var createdAt = Calendar.getInstance()
    var writer = UserData()

}