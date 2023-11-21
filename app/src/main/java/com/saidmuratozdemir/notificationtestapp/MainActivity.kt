package com.saidmuratozdemir.notificationtestapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.saidmuratozdemir.notificationtestapp.databinding.ActivityMainBinding
import com.saidmuratozdemir.notificationtestapp.listeners.ItemClickListener
import com.saidmuratozdemir.notificationtestapp.model.CardModel


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeCardList = listOf(
            CardModel(
                "firebase_config",
                "Enter Firebase Configurations",
                "Enter Firebase configurations or import google-services.json file",
                R.drawable.settings
            ),
            CardModel(
                "notification_permission",
                "Check Notification Permission",
                "Check if notification permission is granted",
                R.drawable.check
            ),
            CardModel(
                "see_token",
                "See Device Token",
                "See Firebase token that is generated for your device",
                R.drawable.phone
            ),
            CardModel(
                "notification_history",
                "See Notification History",
                "See notifications that has been sent to your device",
                R.drawable.history
            ),
            CardModel("language", "Language", "Select language", R.drawable.language),
            CardModel("about_us", "About Us", "See information about us", R.drawable.info)

        )

        val cardAdapter = CardAdapter(itemClickListener)
        cardAdapter.setList(homeCardList)
        binding.recyclerView.adapter = cardAdapter

    }

    private val itemClickListener = object : ItemClickListener {
        override fun onClick(objects: Any?) {
            val cardModel = objects as CardModel

            when (cardModel.id) {
                "firebase_config" -> {
                    val intent = Intent(this@MainActivity, FirebaseConfigActivity::class.java)
                    startActivity(intent)

                }

                "notification_permission" -> {
                    // TODO: write
                }

                "see_token" -> {
                    val intent = Intent(this@MainActivity, TokenActivity::class.java)
                    intent.putExtra("token", "tokenString")
                    startActivity(intent)
                }

                "notification_history" -> {
                    val intent = Intent(this@MainActivity, HistoryActivity::class.java)
                    startActivity(intent)
                }
                "language" -> {

                    val checkedItem = intArrayOf(0)
                    // show language dialog with 3 options
                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)

                    // set the custom icon to the alert dialog

                    // set the custom icon to the alert dialog. use deprecated method setIcon() to set icon
                    alertDialog.setIcon(R.drawable.language)

                    // title of the alert dialog

                    // title of the alert dialog
                    alertDialog.setTitle("Choose a Language")

                    // list of the items to be displayed to the user in the
                    // form of list so that user can select the item from

                    // list of the items to be displayed to the user in the
                    // form of list so that user can select the item from
                    val listItems = arrayOf("English", "Turkish", "Spanish")

                    // the function setSingleChoiceItems is the function which
                    // builds the alert dialog with the single item selection

                    // the function setSingleChoiceItems is the function which
                    // builds the alert dialog with the single item selection
                    alertDialog.setSingleChoiceItems(listItems, checkedItem[0]) { dialog, which ->
                        // update the selected item which is selected by the user so that it should be selected
                        // when user opens the dialog next time and pass the instance to setSingleChoiceItems method
                        checkedItem[0] = which

                        // when selected an item the dialog should be closed with the dismiss method
                        dialog.dismiss()
                    }

                    // set the negative button if the user is not interested to select or change already selected item

                    // set the negative button if the user is not interested to select or change already selected item
                    alertDialog.setNegativeButton("Cancel") { _, _ -> }

                    // create and build the AlertDialog instance with the AlertDialog builder instance

                    // create and build the AlertDialog instance with the AlertDialog builder instance
                    val customAlertDialog: AlertDialog = alertDialog.create()

                    // show the alert dialog when the button is clicked

                    // show the alert dialog when the button is clicked
                    customAlertDialog.show()
                }
                "about_us" -> {
                    val intent = Intent(this@MainActivity, AboutUsActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        override fun onLongClick(view: View, objects: Any?) {

        }
    }
}