package com.tioeun.a20191119_01_banklistpractice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tioeun.a201911_kotlinfinaltest.R
import com.tioeun.a201911_kotlinfinaltest.datas.Notice
import java.util.ArrayList

class NoticeAdapter(context: Context, res : Int, list:ArrayList<Notice>) : ArrayAdapter<Notice>(context,res,list) {
    var mContext = context
    var mList = list
    var inf = LayoutInflater.from(mContext)

    constructor(context: Context, list:ArrayList<Notice>) : this(context, R.layout.notice_list_item, list)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if(tempRow == null) {
            tempRow = inf.inflate(R.layout.notice_list_item, null)
        }

        var row = tempRow!!

        var data = mList.get(position)
        var titleTxt = row.findViewById<TextView>(R.id.titleTxt)
        var createdAtTxt = row.findViewById<TextView>(R.id.createdAtTxt)
        var contentTxt = row.findViewById<TextView>(R.id.contentTxt)

        titleTxt.text = data.title
        contentTxt.text = data.content
        createdAtTxt.text = data.getFormattedCreatedAt()


        return row
    }
}