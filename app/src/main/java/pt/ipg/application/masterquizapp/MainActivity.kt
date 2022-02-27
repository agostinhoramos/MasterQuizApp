package pt.ipg.application.masterquizapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class MainActivity : AppCompatActivity() {
    // This function is generated when activity class is generated
    override fun onCreate(savedInstanceState: Bundle?) {
        // Hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // This call the parent constructor
        super.onCreate(savedInstanceState)

        // This is used to align the XML view to this class
        setContentView(R.layout.activity_main)

        // this function to call the button
        val btnStart = findViewById<Button>(R.id.btn_start)
        btnStart.setOnClickListener {
            // Do some action
        }
    }


}