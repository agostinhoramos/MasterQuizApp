package pt.ipg.application.masterquizapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor

data class User(var name: String, var country: String, var created_at: String, var id: Long = -1) {
    fun toContentValues() : ContentValues {
        val values = ContentValues()

        values.put(TableUsers.COLUMN_NAME, name)
        values.put(TableUsers.COLUMN_COUNTRY, country)
        values.put(TableUsers.COLUMN_CREATED_AT, created_at)

        return values
    }

    companion object {
        @SuppressLint("Range")
        fun fromCursor(cursor: Cursor) = User(
            name = cursor.getString(cursor.getColumnIndex(TableUsers.COLUMN_NAME)),
            country = cursor.getString(cursor.getColumnIndex(TableUsers.COLUMN_COUNTRY)),
            created_at = cursor.getString(cursor.getColumnIndex(TableUsers.COLUMN_CREATED_AT)),
            id = cursor.getLong(cursor.getColumnIndex(TableDB.COLUMN_ID))
        )
    }
}