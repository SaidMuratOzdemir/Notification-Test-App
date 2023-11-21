package com.saidmuratozdemir.notificationtestapp

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.saidmuratozdemir.notificationtestapp.databinding.ActivityTokenBinding

class TokenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTokenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTokenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val token = intent.getStringExtra("token")
        binding.textviewToken.text = token


    }

    private fun copyButton() {
        binding.copyButton.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(
                android.content.ClipData.newPlainText(
                    "token",
                    binding.textviewToken.text
                )
            )
            Snackbar.make(
                binding.root,
                "Token copied to clipboard",
                Snackbar.LENGTH_SHORT
            ).show()
        }

    }

    private fun shareButton() {
        binding.shareButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, binding.textviewToken.text)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}