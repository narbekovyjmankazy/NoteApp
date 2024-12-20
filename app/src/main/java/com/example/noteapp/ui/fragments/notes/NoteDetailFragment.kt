package com.example.noteapp.ui.fragments.notes

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.noteapp.App
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDate()
        changeListener()
        setupListeners()
    }

    @SuppressLint("NewApi")
    private fun setDate(): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("d MMMM HH:mm", Locale("ru"))
        val formattedDateTime = currentDateTime.format(formatter)
        binding.tvDate.text = formattedDateTime
        return formattedDateTime
    }

    private fun setupListeners() = with(binding){
        ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        tvReady.setOnClickListener {
                App.appDatabase?.noteDao()?.insertNote(NoteModel(etTitle.text.toString(), etDesc.text.toString(), setDate()))
                findNavController().navigateUp()
        }
    }

    private fun changeListener() {
        binding.etTitle.addTextChangedListener { checkFields() }
        binding.etDesc.addTextChangedListener { checkFields() }
    }

    fun checkFields() = with(binding){
        val params = ivColorMenu.layoutParams as ViewGroup.MarginLayoutParams
        if (etTitle.text.toString().isNotEmpty() || etDesc.text.toString().isNotEmpty()) {
            tvReady.visibility = View.VISIBLE
            params.marginStart = 200
        } else {
            tvReady.visibility = View.GONE
            params.marginStart = 424
        }
        ivColorMenu.layoutParams = params
    }
}