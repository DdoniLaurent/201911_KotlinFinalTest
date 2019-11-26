package com.tioeun.a201911_kotlinfinaltest.utils

import android.content.Context

class ContextUtil {
    companion object {

        val prefName = "KotlinFinalTestPrefference"

//        아이디 저장 여부 기록 항목
        val SAVE_ID_CHECKED = "SAVE_ID_CHECKED"

        //        사용자의 아이디를 저장하는 항목 이름
        val USER_ID = "USER_ID"

        //        아이디 기억저장 여부
        val ID_REMEMBER = "ID_REMEMBER"

        //        사용자 고유값(토큰값)을 저장하는 항목 이름
        val USER_TOKEN = "USER_TOKEN"

        fun setSaveIdChecked(context:Context, isChecked:Boolean) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(SAVE_ID_CHECKED, isChecked).apply()
        }

        fun getSaveIdChecked(context: Context) : Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(SAVE_ID_CHECKED, false)
        }
        //        사용자 아이디를 저장해주는 func
        fun setUserId(context: Context, userId:String) {

//          메모장(파일이름 : PracticePrefference)을 실제로 여는 동작
            var pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

//          내용을 작성하고 SAVE 버튼을 누름.
            pref.edit().putString(USER_ID, userId).apply()
        }

        fun getUserId(context: Context) : String {

//          메모장(파일이름 : PracticePrefference)을 실제로 여는 동작
            var pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            return pref.getString(USER_ID, "")!!
        }

//===================================================

        fun setRemember(context: Context, isRemember:Boolean) {

//          메모장(파일이름 : PracticePrefference)을 실제로 여는 동작
            var pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

//          내용을 작성하고 SAVE 버튼을 누름.
            pref.edit().putBoolean(ID_REMEMBER, isRemember).apply()
        }

        fun getIdRemember(context: Context) : Boolean {

//          메모장(파일이름 : PracticePrefference)을 실제로 여는 동작
            var pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            return pref.getBoolean(ID_REMEMBER, false)!!
        }
        //===================================================
        fun setUserToken(context: Context, userToken:String) {

//          메모장(파일이름 : PracticePrefference)을 실제로 여는 동작
            var pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

//          내용을 작성하고 SAVE 버튼을 누름.
            pref.edit().putString(USER_TOKEN, userToken).apply()
        }

        fun getUserToken(context: Context) : String {

//          메모장(파일이름 : PracticePrefference)을 실제로 여는 동작
            var pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            return pref.getString(USER_TOKEN, "")!!
        }
    }
}