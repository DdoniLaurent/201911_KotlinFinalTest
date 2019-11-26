package com.tioeun.a201911_kotlinfinaltest.utils

import android.content.Context

class ContextUtil {
    companion object {

        val prefName = "KotlinFinalTestPreference"

        //        아이디 저장 여부 기록 항목
        val SAVE_ID_CHECKED = "SAVE_ID_CHECKED"

        val USER_ID = "USER_ID"

        val USER_ID_CHECKED = "USER_ID_CHECKED"

        val USER_TOKEN = "USER_TOKEN"

        fun setSaveIdChecked(context:Context, isChecked:Boolean) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(SAVE_ID_CHECKED, isChecked).apply()
        }

        fun getSaveIdChecked(context: Context) : Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(SAVE_ID_CHECKED, false)
        }

 //=========================================================
        fun setUserToken(context: Context, userToken: String){

            //메모장(파일이름 : PracticePrefference)을 실제로 여는 동작
            var pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)

            //내용을 작성하고 SAVE 버튼 누름
            pref.edit().putString(USER_TOKEN, userToken).apply()
        }

        fun getUserToken(context: Context) : String{
            var pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)

            return pref.getString(USER_TOKEN,"")!!
        }
//===========================================================

        fun setUserId(context: Context, userId: String){

            //메모장(파일이름 : PracticePrefference)을 실제로 여는 동작
            var pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)

            //내용을 작성하고 SAVE 버튼 누름
            pref.edit().putString(USER_ID, userId).apply()
        }

        fun getUserId(context: Context) : String{
            var pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)

            return pref.getString(USER_ID,"")!!
        }

//        ======================================================
    }
}