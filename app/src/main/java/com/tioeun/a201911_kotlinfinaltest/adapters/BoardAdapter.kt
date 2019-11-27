package com.tioeun.a20191119_01_banklistpractice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.tioeun.a201911_kotlinfinaltest.R
import com.tioeun.a201911_kotlinfinaltest.datas.BlackList
import java.util.ArrayList

class BoardAdapter(context: Context, res : Int, list:ArrayList<BlackList>) : ArrayAdapter<BlackList>(context,res,list) {
    var mContext = context
    var mList = list
    var inf = LayoutInflater.from(mContext)

    constructor(context: Context, list:ArrayList<BlackList>) : this(context, R.layout.board_list_item, list)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if(tempRow == null) {
            tempRow = inf.inflate(R.layout.board_list_item, null)
        }

        var row = tempRow!!

        var data = mList.get(position)


        return row
    }
}