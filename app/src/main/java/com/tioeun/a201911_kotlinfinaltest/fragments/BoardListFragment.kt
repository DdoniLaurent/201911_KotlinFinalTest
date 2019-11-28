package com.tioeun.a201911_kotlinfinaltest.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tioeun.a20191119_01_banklistpractice.adapters.BoardAdapter
import com.tioeun.a201911_kotlinfinaltest.EditBlackListActivity
import com.tioeun.a201911_kotlinfinaltest.R
import com.tioeun.a201911_kotlinfinaltest.datas.BlackList
import com.tioeun.a201911_kotlinfinaltest.utils.ServerUtil
import kotlinx.android.synthetic.main.fragment_board_list.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BoardListFragment : BaseFragment() {

    var dateFilterStartDate:Calendar? = null

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

    override fun onResume() {
        super.onResume()
        getBlackListFromServer()
    }

    override fun setupEvents() {

//        날짜 필터 선택을 누르면 => 며칠부터 필터를 하고싶은지 DatePicker로 선택
//        선택 결과를 텍스트뷰에 반영
//        dateFilterStateDate가 null이면 초기화. 년/월/일 세팅
//        날짜는 2019.09.08 ~ 양식으로 반영
        dateFilterBtn.setOnClickListener {
            var datePickerDialog = DatePickerDialog(mContext!!,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(mContext, "${year}년 ${month+1}월 ${dayOfMonth}일",Toast.LENGTH_LONG).show()


                var selectDate = Calendar.getInstance()
                selectDate.set(year,month,dayOfMonth)

                var sdf = SimpleDateFormat("yyyy년M월d일")
                var resultStr = sdf.format(selectDate.time)


                dateFilterTxt.text = resultStr
            },2019,Calendar.NOVEMBER,8)


            datePickerDialog.show()
        }

        writeBlackListBtn.setOnClickListener {
            val intent = Intent(mContext!!, EditBlackListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun setValue() {

        blackListAdapter = BoardAdapter(mContext!!, blackList)
        boardListView.adapter = blackListAdapter

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