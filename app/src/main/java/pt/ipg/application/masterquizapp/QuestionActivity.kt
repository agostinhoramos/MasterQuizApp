package pt.ipg.application.masterquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import pt.ipg.application.masterquizapp.`object`.Question

class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 0
    private var mQuestionList: ArrayList<Question>? = null
    private var mQuestionSize: Int = 0
    private var mSelectedOptionPosition: Int = 0
    private var progressBar: ProgressBar? = null

    //
    private var btnSubmit: Button? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var tvQuestion: TextView? = null

    private var numCorrect: Int = 0
    private var score: Float = 0.0F
    private var prevCorrectAnswer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        btnSubmit = findViewById(R.id.btn_submit)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        tvQuestion = findViewById(R.id.tv_question)

        // Enable on click event
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        progressBar = findViewById(R.id.progressBar)

        mQuestionSize = Constants.getQuestions().size
        progressBar?.max = mQuestionSize
        mQuestionList = Constants.getQuestions()
        nextQuestion() // Display first question at begining

        // style option
        defaultOptionsView()
    }

    private fun nextQuestion() {

        // Logic - Check if Answer is correct
        if (prevCorrectAnswer != 0 &&
            prevCorrectAnswer == mSelectedOptionPosition) {
            numCorrect += 1
        }

        if( mCurrentPosition < mQuestionSize ){
            mCurrentPosition += 1

            val question =
                mQuestionList!![mCurrentPosition - 1]

            progressBar?.progress = mCurrentPosition
            findViewById<TextView>(R.id.tv_progress).text =
                "${mCurrentPosition} / ${progressBar?.getMax()}"

            tvQuestion?.text = question.question
            tvOptionOne?.text = question.optionOne
            tvOptionTwo?.text = question.optionTwo
            tvOptionThree?.text = question.optionThree
            tvOptionFour?.text = question.optionFour

            prevCorrectAnswer = question.correctAnswer

        } else {
            // GO TO NEXT ACTIVITY
            val myIntent = intent
            var userName = myIntent?.getStringExtra("user_name")

            val intent = Intent(this@QuestionActivity, FinalResultActivity::class.java)
            score = ((100 * numCorrect)/mQuestionSize).toFloat() // parse to percent

            intent.putExtra("user_name", userName )
            intent.putExtra("final_score", score.toString())
            startActivity(intent)
            finish()
        }

        if (mCurrentPosition == mQuestionSize){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
    }

    // set click event
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(findViewById(R.id.tv_option_one), 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(findViewById(R.id.tv_option_two), 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(findViewById(R.id.tv_option_three), 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(findViewById(R.id.tv_option_four), 4)
            }
            R.id.btn_submit -> {
                if( mSelectedOptionPosition != 0 ){
                    nextQuestion()
                    defaultOptionsView()
                }else{
                    Toast.makeText(this, "Please, select one option", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuestionActivity,
            R.drawable.selected_option_border_bg
        )
    }

    private fun defaultOptionsView() {
        mSelectedOptionPosition = 0
        val options = ArrayList<TextView>()
        options.add(0, findViewById(R.id.tv_option_one))
        options.add(1, findViewById(R.id.tv_option_two))
        options.add(2, findViewById(R.id.tv_option_three))
        options.add(3, findViewById(R.id.tv_option_four))
        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuestionActivity,
                R.drawable.default_option_border_bg
            )
        }
    }
}