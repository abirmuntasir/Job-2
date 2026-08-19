package com.example.job2

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.job2.databinding.ActivityProfileListBinding
import com.example.job2.viewmodel.UserViewModel

class ProfileListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileListBinding
    private val viewModel: UserViewModel by viewModels()
    private lateinit var adapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        viewModel.allUsers.observe(this) { users ->
            adapter.submitList(users)
        }

        viewModel.userCount.observe(this) { count ->
            binding.tvTotalCount.text = "Total Profiles: $count"
        }

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddProfileActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        adapter = ProfileAdapter(
            onEdit = { user ->
                val intent = Intent(this, AddProfileActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            },
            onDelete = { user ->
                viewModel.delete(user)
            },
            onClick = { user ->
                val intent = Intent(this, SingleProfileActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }
        )
        binding.rvProfiles.layoutManager = LinearLayoutManager(this)
        binding.rvProfiles.adapter = adapter
    }
}
