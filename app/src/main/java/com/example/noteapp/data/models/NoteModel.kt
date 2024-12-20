package com.example.noteapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "noteModel")
data class NoteModel(
    val title: String,
    val description: String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
