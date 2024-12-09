package com.example.noteapp.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardPagingBinding
import com.example.noteapp.ui.adapters.OnBoardViewPagerAdapter

class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setUpListener()
    }

    private fun initialize() {
        binding.viewPager.adapter = OnBoardViewPagerAdapter(this)
        binding.dotsIndicator.attachTo(binding.viewPager)
    }

    private fun setUpListener() = with(binding.viewPager) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.tvSkip.visibility = View.INVISIBLE
                    binding.btnStart.visibility = View.VISIBLE
                }else{
                    binding.tvSkip.visibility = View.VISIBLE
                    binding.btnStart.visibility = View.GONE
                    binding.tvSkip.setOnClickListener{
                        setCurrentItem(currentItem + 2, true)
                    }
                }
            }
        })
    }
}