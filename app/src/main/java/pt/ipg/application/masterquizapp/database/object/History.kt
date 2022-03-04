package pt.ipg.application.masterquizapp.database.`object`

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import pt.ipg.application.masterquizapp.database.DBTable
import pt.ipg.application.masterquizapp.database.table.*

data class History(var fk_user: Long, var date: String, var level: String, var id: Long = -1) {
    fun toContentValues() : ContentValues {
        val values = ContentValues()

        values.put(HistoryTable.COLUMN_FK_USER, fk_user)
        values.put(HistoryTable.COLUMN_DATE, date)
        values.put(HistoryTable.COLUMN_LEVEL, level)

        return values
    }

    companion object {
        @SuppressLint("Range")
        fun fromCursor(cursor: Cursor) = History(
            fk_user = cursor.getLong(cursor.getColumnIndex(HistoryTable.COLUMN_FK_USER)),
            date = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_DATE)),
            level = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_LEVEL)),
            id = cursor.getLong(cursor.getColumnIndex(DBTable.COLUMN_ID))
        )
    }
}