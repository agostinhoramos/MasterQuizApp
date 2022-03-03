package pt.ipg.application.masterquizapp

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TableQuestion(db : SQLiteDatabase) : TableDB(db, TABLE_NAME) {
    fun create() {
        db.execSQL("CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_OWNER TEXT, $COLUMN_QUESTION TEXT NOT NULL)")
    }

    companion object {
        const val TABLE_NAME = "question"
        const val COLUMN_OWNER = "owner"
        const val COLUMN_QUESTION = "question"

        val ALL_COLUMNS = arrayOf(COLUMN_ID, COLUMN_OWNER, COLUMN_QUESTION)
    }
}