package com.example.noteapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.NoteItemBinding


class NoteAdapter: ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel){
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class DiffCallback: DiffUtil.ItemCallback<NoteModel>(){
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

    }
}