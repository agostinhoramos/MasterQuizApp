package pt.ipg.application.masterquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        // Enable on click event
        findViewById<TextView>(R.id.tv_option_one)
            .setOnClickListener(this)
        findViewById<TextView>(R.id.tv_option_two)
            .setOnClickListener(this)
        findViewById<TextView>(R.id.tv_option_three)
            .setOnClickListener(this)
        findViewById<TextView>(R.id.tv_option_four)
            .setOnClickListener(this)

        mQuestionList = Constants.getQuestions()

        var currentPosition = 1
        var progressBar = findViewById<TextView>(R.id.tv_progress)
        progressBar.text = "$currentPosition / 4"

        setQuestion()
    }

    private fun setQuestion() {

        val question =
            mQuestionList!![mCurrentPosition - 1]

        findViewById<TextView>(R.id.tv_question).text = question.question
        findViewById<TextView>(R.id.tv_option_one).text = question.optionOne
        findViewById<TextView>(R.id.tv_option_two).text = question.optionTwo
        findViewById<TextView>(R.id.tv_option_three).text = question.optionThree
        findViewById<TextView>(R.id.tv_option_four).text = question.optionFour
    }

    // set click event
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_question -> {
                print("You type Question")
            }
            R.id.tv_option_one -> {
                print("You type 1")
            }
            R.id.tv_option_two -> {
                print("You type 2")
            }
            R.id.tv_option_three -> {
                print("You type 3")
            }
            R.id.tv_option_four -> {
                print("You type 4")
            }
        }
    }
}