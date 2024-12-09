package com.example.noteapp.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.noteapp.ui.fragments.onboard.OnBoardFragment
import com.example.noteapp.ui.fragments.onboard.OnBoardFragment.Companion.ARG_ONBOARD_POSITION
import com.example.noteapp.ui.fragments.onboard.OnBoardPagingFragment

class OnBoardViewPagerAdapter(fragment: Fragment):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int) = OnBoardFragment().apply {
        arguments = Bundle().apply {
            putInt(ARG_ONBOARD_POSITION, position)
        }
    }
}