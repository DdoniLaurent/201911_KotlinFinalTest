package com.tioeun.a201911_kotlinfinaltest

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lotto.*
import java.util.*

class LottoActivity : BaseActivity() {

    var mHandller = Handler()

    var isNowBuying = false

    var usedMoney = 0L
    var luckyMoney = 0L
    var firstRankCount = 0
    var secondRankCount = 0
    var thirdRankCount = 0
    var fourthRankCount = 0
    var fifthRankCount = 0
    var wrongRankCount = 0


    var lottoNumArrayList = ArrayList<Int>()
    var bonusNum = 0

    var thisWeekLottoNumTextViewArrayList = ArrayList<TextView>()

    var myNumArrayList = ArrayList<Int>()
    var myTextViewArrayList = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto)
        setupEvents()
        setValues()
    }



    override fun setupEvents() {

        //로또 하나 구매하기
        buyOneLottoBtn.setOnClickListener {
            setThisWeekLottoNum()
            checkLottoRank()

            usedMoney += 1000
            usedMoneyTxt.text = String.format("사용금액 : %,d원", usedMoney)
        }

        //로또 자동으로 구매하기
        autoLottoBtn.setOnClickListener {
            if(!isNowBuying) {
                doLottoLoop()
                isNowBuying = true
                autoLottoBtn.text = "구매 중단"
            } else {
                stopLottoLoop()

                isNowBuying = false
                autoLottoBtn.text = "자동 구매 재개"
            }

        }

    }


    //자동구매 스레드? 설정
    var lottoRunnable = object : Runnable {
        override fun run() {
            //구매 금액이 1천억이 안되면서 계속 구매
            if(usedMoney < 1000000000){
                setThisWeekLottoNum()
                checkLottoRank()
                usedMoney += 1000
                usedMoneyTxt.text = String.format("사용금액 : %,d원", usedMoney)

                doLottoLoop()
            } else {
                runOnUiThread {
                    Toast.makeText(mContext, "로또구매를 종료합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //자동구매 스레드?
    fun doLottoLoop() {
        mHandller.post(lottoRunnable)
    }

    //자동구매 스레드? 종료
    fun stopLottoLoop(){
        mHandller.removeCallbacks(lottoRunnable)
    }

    //당첨확인
    fun checkLottoRank() {

        var correctCount = 0
        for(myNum in myNumArrayList) {
            for (thisWeekNum in lottoNumArrayList) {
                if(myNum == thisWeekNum) {
                    correctCount ++
                }
            }
        }
        if(correctCount == 6) {
            Toast.makeText(mContext, "1등 당첨!", Toast.LENGTH_SHORT).show()
            luckyMoney += 2000000000
            firstRankCount ++
        } else if(correctCount == 5) {

            var isSecondRank = false
            for (num in myNumArrayList){
                if(num == bonusNum) {
                    isSecondRank == true
                    break
                }
            }
            if(isSecondRank) {
                luckyMoney += 65000000
                secondRankCount ++
            } else {
                luckyMoney += 1500000
                thirdRankCount ++
            }


        } else if(correctCount == 4) {
            Toast.makeText(mContext, "4등 당첨!", Toast.LENGTH_SHORT).show()
            luckyMoney += 50000
            fourthRankCount ++
        } else if(correctCount == 3) {
            Toast.makeText(mContext, "5등 당첨!", Toast.LENGTH_SHORT).show()
//            luckyMoney += 5000
            usedMoney -= 5000
            fifthRankCount ++
        } else  {
            Toast.makeText(mContext, "꽝입니다.", Toast.LENGTH_SHORT).show()
            luckyMoney += 0
            wrongRankCount ++
        }
        luckyMoneyTxt.text = String.format("누적 당첨 금액 : %,d", luckyMoney)
        firstRankCountTxt.text = String.format("1등 당첨 : %,d", firstRankCount)
        secondRankCountTxt.text = String.format("2등 당첨 : %,d", secondRankCount)
        thirdRankCountTxt.text = String.format("3등 당첨 : %,d", thirdRankCount)
        fourthRankCountTxt.text = String.format("4등 당첨 : %,d", fourthRankCount)
        fifthRankCountTxt.text = String.format("5등 당첨 : %,d", fifthRankCount)
        wrongRankCountTxt.text = String.format("낙점 횟수 : %,d", wrongRankCount)
    }

    fun setThisWeekLottoNum() {

        lottoNumArrayList.clear()

        for (lottoNumTxt in thisWeekLottoNumTextViewArrayList) {

            var randomNum = 0

            while (true) {

                var isDuplOk = true

                randomNum = (Math.random() * 45 + 1).toInt()

                for (num in lottoNumArrayList){

                    if(num == randomNum) {
                        isDuplOk = false
                        break
                    }
                }
                if(isDuplOk) {
                    lottoNumArrayList.add(randomNum)
                    break
                }
            }
        }
        Collections.sort(lottoNumArrayList)

        while(true) {
            var isDulpOk = true
            var tempRandomNum = (Math.random() * 45 +1).toInt()
            for(num in lottoNumArrayList){
                if(tempRandomNum == num) {
                    isDulpOk = false
                    break
                }
            }

            if(isDulpOk) {
                bonusNum = tempRandomNum
                break
            }
        }

        for (i in 0..lottoNumArrayList.size-1) {
            var numTxt = thisWeekLottoNumTextViewArrayList.get(i)
            var number = lottoNumArrayList.get(i)

            numTxt.text = number.toString()
        }
        bonusNumTxt.text = bonusNum.toString()

    }

    override fun setValues() {
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt1)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt2)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt3)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt4)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt5)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt6)

        myTextViewArrayList.add(myLottoNumTxt1)
        myTextViewArrayList.add(myLottoNumTxt2)
        myTextViewArrayList.add(myLottoNumTxt3)
        myTextViewArrayList.add(myLottoNumTxt4)
        myTextViewArrayList.add(myLottoNumTxt5)
        myTextViewArrayList.add(myLottoNumTxt6)

        for (myTv in myTextViewArrayList) {
            myNumArrayList.add(myTv.text.toString().toInt())
        }

    }


}
