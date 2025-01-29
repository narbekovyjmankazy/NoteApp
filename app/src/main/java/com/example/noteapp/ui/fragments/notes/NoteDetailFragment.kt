package com.example.noteapp.ui.fragments.notes

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import com.example.noteapp.utils.PreferenceHelper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private var noteId: Int = -1
    private var color: Int? = null

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
        updateNote()
        setupListeners()
    }


    @SuppressLint("NewApi")
    private fun setDate(): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("d MMMM HH:mm", Locale("ru"))
        binding.tvDate.text = currentDateTime.format(formatter)
        return currentDateTime.format(formatter)
    }

    private fun setupListeners() = with(binding){
        ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        tvReady.setOnClickListener {
            if (noteId != -1) {
                val updateNote = NoteModel(etTitle.text.toString(), etDesc.text.toString(), setDate(), color.hashCode())
                updateNote.id = noteId
                App.appDatabase?.noteDao()?.updateNote(updateNote)
            } else {
                App.appDatabase?.noteDao()?.insertNote(NoteModel(etTitle.text.toString(), etDesc.text.toString(), setDate(), color.hashCode()))
            }
            findNavController().navigateUp()
        }
        ivColorMenu.setOnClickListener {
            showCustomDialog()
        }
    }

    private fun updateNote() {
        arguments?.let { args ->
            noteId = args.getInt("noteId", -1)
        }
        if (noteId != -1) {
            val id = App.appDatabase?.noteDao()?.getById(noteId)
            id?.let { model ->
                binding.etTitle.setText(model.title)
                binding.etDesc.setText(model.description)
            }
        }
    }

    private fun changeListener() {
        binding.etTitle.addTextChangedListener(textWatcher)
        binding.etDesc.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            checkFields()
        }
    }

    fun checkFields() = with(binding){
        val params = ivColorMenu.layoutParams as ViewGroup.MarginLayoutParams
        params.marginStart = if (etTitle.text.toString().isNotEmpty() || etDesc.text.toString().isNotEmpty()) {
            tvReady.visibility = View.VISIBLE
            232
        } else {
            tvReady.visibility = View.GONE
            436
        }
        ivColorMenu.layoutParams = params
    }
    private fun showCustomDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.color_alert_dialog, null)
        val dialog = AlertDialog.Builder(requireContext()).setView(dialogView).create()
        dialog.window?.setDimAmount(0f) // Make the background fragment visible
        val params = dialog.window?.attributes
        params?.gravity = Gravity.END or Gravity.TOP
        params?.y = binding.ivColorMenu.height // Adjust so the top is aligned with the bottom of ivColorMenu
        dialog.window?.attributes = params

        val colors = listOf(
            R.id.color_yellow to R.color.yellow,
            R.id.color_purple to R.color.purple,
            R.id.color_pink to R.color.pink,
            R.id.color_red to R.color.red,
            R.id.color_green to R.color.green,
            R.id.color_blue to R.color.blue,
        )
        colors.forEach { (viewId, colorRes) ->
            dialogView.findViewById<View>(viewId).setOnClickListener {
                color = ContextCompat.getColor(requireContext(), colorRes)
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}