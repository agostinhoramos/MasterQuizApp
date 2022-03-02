package pt.ipg.application.masterquizapp

data class Question (
    val id: Int, // Any int value
    val question: String, // Any string value
    val optionOne: String, // Any string value
    val optionTwo: String, // Any string value
    val optionThree: String, // Any string value
    val optionFour: String, // Any string value
    val correctAnswer: Int // Start from 1
)