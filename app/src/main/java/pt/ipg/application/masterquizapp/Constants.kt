package pt.ipg.application.masterquizapp

object Constants {
    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()

        questionList.add(
            Question(1, "1+2", "6", "3", "3", "8", 2)
        )
        questionList.add(
            Question(2, "1-2", "1", "3", "3", "8", 1)
        )
        questionList.add(
            Question(3, "1*2", "6", "3", "3", "2", 4)
        )
        questionList.add(
            Question(4, "35+2", "6", "37", "3", "8", 2)
        )
        questionList.add(
            Question(5, "1+200", "201", "5", "3", "8", 1)
        )

        return questionList
    }
}