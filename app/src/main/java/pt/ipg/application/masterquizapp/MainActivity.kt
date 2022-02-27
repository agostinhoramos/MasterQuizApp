package pt.ipg.application.masterquizapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

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

        // Declare input text 'name'
        val etName = findViewById<AppCompatEditText>(R.id.et_name)

        // this function to call the button
        val btnStart = findViewById<Button>(R.id.btn_start)
        btnStart.setOnClickListener {
            // Do some action
            if (etName.text.toString().isEmpty()){
                Toast.makeText(this@MainActivity, "Please enter your name",
                Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this@MainActivity, QuestionActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


}