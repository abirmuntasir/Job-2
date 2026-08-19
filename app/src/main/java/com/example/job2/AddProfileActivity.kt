package com.example.job2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.job2.data.User
import com.example.job2.databinding.ActivityAddProfileBinding
import com.example.job2.viewmodel.UserViewModel

class AddProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProfileBinding
    private val viewModel: UserViewModel by viewModels()
    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentUser = intent.getSerializableExtra("user") as? User
        if (currentUser != null) {
            populateFields(currentUser!!)
            binding.tvAddProfileHeader.text = "Update User Profile"
            binding.btnSave.text = "Update Profile"
        }

        binding.btnSave.setOnClickListener {
            saveProfile()
        }
    }

    private fun populateFields(user: User) {
        binding.etName.setText(user.name)
        binding.etEmail.setText(user.email)
        binding.etPhone.setText(user.phone)
        binding.etAddress.setText(user.address)
        binding.etBio.setText(user.bio)
    }

    private fun saveProfile() {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()
        val bio = binding.etBio.text.toString().trim()

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Name and Email are required", Toast.LENGTH_SHORT).show()
            return
        }

        if (currentUser == null) {
            val user = User(name = name, email = email, phone = phone, address = address, bio = bio)
            viewModel.insert(user)
            Toast.makeText(this, "Profile Saved", Toast.LENGTH_SHORT).show()
        } else {
            val user = currentUser!!.copy(name = name, email = email, phone = phone, address = address, bio = bio)
            viewModel.update(user)
            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}
