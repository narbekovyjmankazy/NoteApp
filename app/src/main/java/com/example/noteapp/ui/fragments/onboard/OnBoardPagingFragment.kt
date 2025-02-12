package com.example.noteapp.ui.fragments.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding
    companion object{
        const val ARG_ONBOARD_POSITION = "onboard_position"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when(requireArguments().getInt(ARG_ONBOARD_POSITION)){
            0 -> {
                tvDescriptionTitle.text = "Удобство"
                tvDescription.text = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
                lottieAnimationView.setAnimation(R.raw.first_anim)
            }
            1 -> {
                tvDescriptionTitle.text = "Организация"
                tvDescription.text = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
                lottieAnimationView.setAnimation(R.raw.second_anim)
            }
            2 -> {
                tvDescriptionTitle.text = "Синхронизация"
                tvDescription.text = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
                lottieAnimationView.setAnimation(R.raw.third_anim)
            }
        }
    }
}