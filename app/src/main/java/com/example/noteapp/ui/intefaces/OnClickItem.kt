package com.example.noteapp.ui.intefaces

import com.example.noteapp.data.models.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)

    fun onClick(noteModel: NoteModel)
}