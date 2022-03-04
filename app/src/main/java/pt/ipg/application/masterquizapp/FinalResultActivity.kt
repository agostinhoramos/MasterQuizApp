package pt.ipg.application.masterquizapp

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.io.IOException

class FinalResultActivity : AppCompatActivity(), View.OnClickListener {
    private var btnReplay: Button? = null
    private var btnQuit: Button? = null
    private var userName: String? = null

    // Sounds
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_result)

        btnReplay = findViewById(R.id.btn_replay)
        btnQuit = findViewById(R.id.btn_quit)

        btnReplay?.setOnClickListener(this)
        btnQuit?.setOnClickListener(this)

        val myIntent = intent
        userName = myIntent?.getStringExtra("user_name")
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
            .text = getString(R.string.final_score_score, score)
        findViewById<TextView>(R.id.tv_player_name)
            .text = getString(R.string.hey_user, userName)
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

            playAudio()
        }
    }

    private fun playAudio(){
        val audioURL = getString(R.string.mp3_ukraine_url)
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            mediaPlayer!!.setDataSource(audioURL)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
            isPlaying = true
        }catch (e: IOException){
            e.printStackTrace()
        }
        Toast.makeText(this, getString(R.string.ukrainian_anthem), Toast.LENGTH_SHORT).show()
    }

    private fun pauseAudio(){
        if( mediaPlayer!!.isPlaying ){
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.release()
            isPlaying = false
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_replay -> {
                if (isPlaying === true) pauseAudio()
                val intent = Intent(this@FinalResultActivity, QuestionActivity::class.java)
                intent.putExtra("user_name", userName )
                startActivity(intent)
                finish()
            }
            //
            R.id.btn_quit -> {
                if (isPlaying === true) pauseAudio()
                val intent = Intent(this@FinalResultActivity, ListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}