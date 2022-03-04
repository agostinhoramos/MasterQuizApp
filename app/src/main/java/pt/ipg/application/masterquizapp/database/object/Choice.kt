package pt.ipg.application.masterquizapp.database.`object`

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import pt.ipg.application.masterquizapp.database.DBTable
import pt.ipg.application.masterquizapp.database.table.*

data class Choice(var fk_question: Long, var choice: String, var id: Long = -1) {
    fun toContentValues() : ContentValues {
        val values = ContentValues()

        values.put(ChoiceTable.COLUMN_FK_QUESTION, fk_question)
        values.put(ChoiceTable.COLUMN_CHOICE, choice)

        return values
    }

    companion object {
        @SuppressLint("Range")
        fun fromCursor(cursor: Cursor) = Choice(
            fk_question = cursor.getLong(cursor.getColumnIndex(ChoiceTable.COLUMN_FK_QUESTION)),
            choice = cursor.getString(cursor.getColumnIndex(ChoiceTable.COLUMN_CHOICE)),
            id = cursor.getLong(cursor.getColumnIndex(DBTable.COLUMN_ID))
        )
    }
}