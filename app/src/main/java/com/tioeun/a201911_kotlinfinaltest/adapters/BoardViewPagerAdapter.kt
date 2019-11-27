package com.tioeun.a201911_kotlinfinaltest.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tioeun.a201911_kotlinfinaltest.fragments.BoardListFragment
import com.tioeun.a201911_kotlinfinaltest.fragments.NoticeListFragment

class BoardViewPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "게시판"
            else -> "공지사항"
        }
    }

    override fun getItem(position: Int): Fragment {
//        JAVA
//        if(position == 0) {
//            return BoardListFragment()
//        } else {
//            return NoticeListFragment()
//        }
//        KOTLIN
        return when(position) {
            0 -> {
                BoardListFragment()
            } else -> {
                NoticeListFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}