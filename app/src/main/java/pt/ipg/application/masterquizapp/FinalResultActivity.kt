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
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class FinalResultActivity : AppCompatActivity(), View.OnClickListener {
    private var btnReplay: Button? = null
    private var btnQuit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_result)

        btnReplay = findViewById(R.id.btn_replay)
        btnQuit = findViewById(R.id.btn_quit)

        btnReplay?.setOnClickListener(this)
        btnQuit?.setOnClickListener(this)

        val myIntent = intent
        var userName = myIntent?.getStringExtra("user_name")
        val score = myIntent?.getStringExtra("final_score")

        var gameStatus: String? = null
        var isWinner: Boolean? = false

        if (score?.toFloat()!! < 50.0F){
            gameStatus = "YOU LOSE!"
            findViewById<ImageView>(R.id.iv_trophy)
                .setBackgroundResource(R.drawable.ic_you_lose);
            isWinner = false
        }else if (score?.toFloat()!! >= 50.0F){
            gameStatus = "YOU WON!"
            findViewById<ImageView>(R.id.iv_trophy)
                .setBackgroundResource(R.drawable.ukraine_flag);
            isWinner = true
        }

        findViewById<TextView>(R.id.tv_final_score)
            .text = "Final Score: $score%"
        findViewById<TextView>(R.id.tv_player_name)
            .text = "Hey, $userName!"
        findViewById<TextView>(R.id.tv_game_status)
            .text = gameStatus

        if(isWinner == true){

            // Do something..
            SocketHandler.setSocket()
            val mSocket = SocketHandler.getSocket()
            mSocket.connect()

            // action
            var obj = "{\"name\": \"$userName\", \"score\": $score}"
            mSocket.emit("notify", obj)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_replay -> {
                val intent = Intent(this@FinalResultActivity, QuestionActivity::class.java)
                startActivity(intent)
                finish()
            }
            //
            R.id.btn_quit -> {
                val intent = Intent(this@FinalResultActivity, ListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}