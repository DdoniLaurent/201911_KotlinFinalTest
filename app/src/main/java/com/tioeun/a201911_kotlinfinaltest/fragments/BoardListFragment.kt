package com.tioeun.a201911_kotlinfinaltest.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tioeun.a20191119_01_banklistpractice.adapters.BoardAdapter
import com.tioeun.a201911_kotlinfinaltest.EditBlackListActivity
import com.tioeun.a201911_kotlinfinaltest.R
import com.tioeun.a201911_kotlinfinaltest.datas.BlackList
import com.tioeun.a201911_kotlinfinaltest.utils.ServerUtil
import kotlinx.android.synthetic.main.fragment_board_list.*
import org.json.JSONObject

class BoardListFragment : BaseFragment() {

    var blackList = ArrayList<BlackList>()
    var blackListAdapter:BoardAdapter? =null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_board_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = activity

        setupEvents()
        setValue()
    }

    override fun setupEvents() {
        writeBlackListBtn.setOnClickListener {
            val intent = Intent(mContext!!, EditBlackListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun setValue() {

        blackListAdapter = BoardAdapter(mContext!!, blackList)
        boardListView.adapter = blackListAdapter

        getBlackListFromServer()
    }

    fun getBlackListFromServer() {
        ServerUtil.getRequestBlackList(mContext!!, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                val code = json.getInt("code")
                if(code == 200) {
                    val data = json.getJSONObject("data")
                    val blackLists = data.getJSONArray("black_lists")

                    blackList.clear()
                    for(i in 0..blackLists.length()-1){
                        blackList.add(BlackList.getBlackListDataFromJson(blackLists.getJSONObject(i)))
                    }

                    activity!!.runOnUiThread {
                        blackListAdapter?.notifyDataSetChanged()
                    }
                }
            }

        })
    }
}