package com.example.job2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.job2.data.User
import com.example.job2.databinding.ActivitySingleProfileBinding

class SingleProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingleProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getSerializableExtra("user") as? User
        user?.let {
            binding.tvDetailName.text = it.name
            binding.tvDetailEmail.text = it.email
            binding.tvDetailPhone.text = it.phone
            binding.tvDetailAddress.text = it.address
            binding.tvDetailBio.text = it.bio
        }
    }
}
