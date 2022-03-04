package pt.ipg.application.masterquizapp.data

import pt.ipg.application.masterquizapp.`object`.Question

object Constants {
    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()

        questionList.add(
            Question(1, "Why have Russian troops attacked?", "Wrong answer", "Russia's stated aim is that Ukraine be freed from oppression and \"cleansed of the Nazis\".", "Wrong answer", "Wrong answer", 2)
        )
        questionList.add(
            Question(2, "What does Putin want?", "His long-term ambitions for Ukraine are unknown.", "Wrong answer", "Wrong answer", "Wrong answer", 1)
        )
        questionList.add(
            Question(3, "How has the world responded?", "Wrong answer", "Both America and Europe promised far tougher measures if Russia went as far as a full invasion of Ukraine.", "Wrong answer", "Wrong answer", 2)
        )
        questionList.add(
            Question(4, "Why did Russia invade?", "Wrong answer", "Wrong answer", "Wrong answer", "Mr. Putin has long sought to assert some control over his neighbor, which was once part of the Russian empire and the Soviet Union.", 4)
        )
        questionList.add(
            Question(5, "Who is the president of Ukraine nowadays?", "Wrong answer", "Volodymyr Zelensky", "Wrong answer", "Wrong answer", 2)
        )

        return questionList
    }
}