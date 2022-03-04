package pt.ipg.application.masterquizapp.database.`object`

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import pt.ipg.application.masterquizapp.database.DBTable
import pt.ipg.application.masterquizapp.database.table.*

data class Question(var owner: String, var question: String, var id: Long = -1) {
    fun toContentValues() : ContentValues {
        val values = ContentValues()

        values.put(QuestionTable.COLUMN_OWNER, owner)
        values.put(QuestionTable.COLUMN_QUESTION, question)

        return values
    }

    companion object {
        @SuppressLint("Range")
        fun fromCursor(cursor: Cursor) = Question(
            owner = cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OWNER)),
            question = cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_QUESTION)),
            id = cursor.getLong(cursor.getColumnIndex(DBTable.COLUMN_ID))
        )
    }
}