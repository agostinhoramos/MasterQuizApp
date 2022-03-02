package pt.ipg.application.masterquizapp.function

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import pt.ipg.application.masterquizapp.ListActivity
import pt.ipg.application.masterquizapp.R

class Others : AppCompatActivity() {
    val CHANEL_ID = "channelID"
    val CHANEL_NAME = "channelName"
    val NOTIFICATION_ID = 0

    fun setNotification(context: Context, name: String){
        val intent = Intent(context, ListActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        var notification = NotificationCompat.Builder(context, CHANEL_ID)
            .setContentTitle("Top Ranking")
            .setContentText("New ranking result from $name")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        // Action ..
        NotificationManagerCompat.from(context)
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