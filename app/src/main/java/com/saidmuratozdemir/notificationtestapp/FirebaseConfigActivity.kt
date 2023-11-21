package com.saidmuratozdemir.notificationtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saidmuratozdemir.notificationtestapp.databinding.ActivityFirebaseConfigBinding
import com.saidmuratozdemir.notificationtestapp.model.FirebaseConfig

class FirebaseConfigActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirebaseConfigBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebaseConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //if user already saved firebase config, then show it
        val sharedPreferences = getSharedPreferences("firebaseConfig", MODE_PRIVATE)
        val firebaseConfigString = sharedPreferences.getString("firebaseConfig", null)
        if (firebaseConfigString != null) {
            val firebaseConfig = FirebaseConfig.fromString(firebaseConfigString)
            binding.editTextFirebaseUrl.setText(firebaseConfig.url)
            binding.editTextFirebaseProjectId.setText(firebaseConfig.projectId)
            binding.editTextFirebaseEmail.setText(firebaseConfig.email)
            binding.editTextFirebaseApiKey.setText(firebaseConfig.apiKey)
        }

        binding.buttonSaveFirebaseConfig.setOnClickListener {

            val url = binding.editTextFirebaseUrl.text.toString()
            val projectId = binding.editTextFirebaseProjectId.text.toString()
            val email = binding.editTextFirebaseEmail.text.toString()
            val apiKey = binding.editTextFirebaseApiKey.text.toString()

            val firebaseConfig = FirebaseConfig(url, projectId, email, apiKey)


            val sharedPreferences = getSharedPreferences("firebaseConfig", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("firebaseConfig", firebaseConfig.toString())
            editor.apply()

        }
    }
}