package pt.ipg.application.masterquizapp.database.`object`

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import pt.ipg.application.masterquizapp.database.DBTable
import pt.ipg.application.masterquizapp.database.table.*

data class UserChoice(var fk_user: Long, var fk_choice: Long, var id: Long = -1) {
    fun toContentValues() : ContentValues {
        val values = ContentValues()

        values.put(UserChoiceTable.COLUMN_FK_USER, fk_user)
        values.put(UserChoiceTable.COLUMN_FK_CHOICE, fk_choice)

        return values
    }

    companion object {
        @SuppressLint("Range")
        fun fromCursor(cursor: Cursor) = UserChoice(
            fk_user = cursor.getLong(cursor.getColumnIndex(UserChoiceTable.COLUMN_FK_USER)),
            fk_choice = cursor.getLong(cursor.getColumnIndex(UserChoiceTable.COLUMN_FK_CHOICE)),
            id = cursor.getLong(cursor.getColumnIndex(DBTable.COLUMN_ID))
        )
    }
}