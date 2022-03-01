package pt.ipg.application.masterquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

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
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_replay -> {
                val intent = Intent(this@FinalResultActivity, QuestionActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btn_quit -> {
                val intent = Intent(this@FinalResultActivity, ListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}