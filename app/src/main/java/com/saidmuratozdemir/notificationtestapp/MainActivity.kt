package com.saidmuratozdemir.notificationtestapp

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.NotificationManager
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MainActivity : AppCompatActivity() {
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private val notificationManager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // android library module nasıl yapılıyor?

        checkPermissionButton()

        copyButton()

        shareButton()

    }

    private fun copyButton() {
        findViewById<Button>(R.id.copy_button).setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(
                android.content.ClipData.newPlainText(
                    "token",
                    "copyString"
                )
            )
            Snackbar.make(
                findViewById<View>(android.R.id.content).rootView,
                "Token copied to clipboard",
                Snackbar.LENGTH_LONG
            ).show()
        }

    }

    private fun shareButton() {
        findViewById<Button>(R.id.share_button).setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "tokenString")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    private fun checkPermissionButton() {


//        // Sets up notification channel.
//        createNotificationChannel()

        // Sets up button.
        findViewById<Button>(R.id.button_check_notification_permission).setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    POST_NOTIFICATIONS,
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Snackbar.make(
                    findViewById<View>(android.R.id.content).rootView,
                    "Permission already granted",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                requestPermissionLauncher.launch(POST_NOTIFICATIONS)
            }
        }

        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                Snackbar.make(
                    findViewById<View>(android.R.id.content).rootView,
                    "Permission granted",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                Snackbar.make(
                    findViewById<View>(android.R.id.content).rootView,
                    "Swiped away or denied permission. You can grant it from settings.",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun subscribeToTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("youtube-yeni-video")
            .addOnCompleteListener { task: Task<Void> ->
                if (task.isSuccessful) {
                    println("Successfully subscribed to topic")
                } else {
                    println("Failed to subscribe to topic")
                }
            }
    }
}