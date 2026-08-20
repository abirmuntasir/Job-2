package com.example.job2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.job2.data.User
import com.example.job2.databinding.ItemProfileBinding

class ProfileAdapter(
    private val onEdit: (User) -> Unit,
    private val onDelete: (User) -> Unit,
    private val onClick: (User) -> Unit
) : ListAdapter<User, ProfileAdapter.ProfileViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProfileViewHolder(private val binding: ItemProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.tvName.text = user.name
            binding.tvEmail.text = user.email
            binding.tvPhone.text = user.phone
            binding.tvAddress.text = user.address
            binding.tvBio.text = user.bio
            binding.btnEdit.setOnClickListener { onEdit(user) }
            binding.btnDelete.setOnClickListener { onDelete(user) }
            binding.root.setOnClickListener { onClick(user) }
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
    }
}
