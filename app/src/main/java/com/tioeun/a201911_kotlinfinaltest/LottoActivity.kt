package com.tioeun.a201911_kotlinfinaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_lotto.*
import java.util.ArrayList

class LottoActivity : BaseActivity() {

    var thisWeekLottoNumArrayList = ArrayList<Int>()
    var thisWeekLottoNumTextViewArrayList = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        buyOneLottoBtn.setOnClickListener {
//            숫자를 랜덤으로 6개 생성. 1~ 45 / 중복되면 안됨.
            setThisWeekLottoNum()

        }
    }

    fun setThisWeekLottoNum() {

        for (lottoNumTxt in thisWeekLottoNumTextViewArrayList) {
            var randomNum = 0
            while (true) {

                var isDuplOk = true

                randomNum = (Math.random() * 45 + 1).toInt()

                for (num in thisWeekLottoNumArrayList){
                    if(num == randomNum) {
//                        중복되는 숫자를 발견!
                        isDuplOk = false
                        break
                    }
                }

                if(isDuplOk) {
                    thisWeekLottoNumArrayList.add(randomNum)
                    break
                }
            }




            lottoNumTxt.text = randomNum.toString()
        }


    }

    override fun setValues() {
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt1)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt2)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt3)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt4)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt5)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt6)
    }


}
