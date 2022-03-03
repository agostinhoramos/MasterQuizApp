package pt.ipg.application.masterquizapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    val CHANEL_ID = "channelID"
    val CHANEL_NAME = "channelName"
    val NOTIFICATION_ID = 0

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
        val userName = findViewById<AppCompatEditText>(R.id.et_name)

        // this function to call the button
        findViewById<Button>(R.id.btn_start)
            .setOnClickListener {
            // Do some action

            if (userName.text.toString().isEmpty()){
                Toast.makeText(this@MainActivity, "Please enter your name",
                Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this@MainActivity, QuestionActivity::class.java)
                intent.putExtra("user_name", userName.text.toString() )
                startActivity(intent)
                finish()
            }
        }

        createNotificationChannel()

        SocketHandler.setSocket()
        val mSocket = SocketHandler.getSocket()
        mSocket.connect()

        mSocket.on("notify"){ args ->
            if (args[0] != null){
                val name = args[0] as String
                runOnUiThread{
                    setNotification(name)
                }
            }
        }

    }

    fun setNotification(name: String){
        val intent = Intent(this, ListActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        var notification = NotificationCompat.Builder(this, CHANEL_ID)
            .setContentTitle("Top Ranking")
            .setContentText("New ranking result from $name")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        // Action ..
        NotificationManagerCompat.from(this)
            .notify(NOTIFICATION_ID, notification)
    }

    fun createNotificationChannel(){
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            val channel = NotificationChannel(CHANEL_ID, CHANEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

}