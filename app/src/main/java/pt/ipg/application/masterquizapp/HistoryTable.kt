package pt.ipg.application.masterquizapp

import android.database.sqlite.SQLiteDatabase

class HistoryTable(db : SQLiteDatabase) : DBTable(db, TABLE_NAME) {
    fun create() {
        db.execSQL("CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_FK_USER TEXT NOT NULL, $COLUMN_DATE TEXT NOT NULL, $COLUMN_LEVEL TEXT NOT NULL)")
    }

    companion object {
        const val TABLE_NAME = "history"
        const val COLUMN_FK_USER = "fk_user"
        const val COLUMN_DATE = "date"
        const val COLUMN_LEVEL = "level"

        val ALL_COLUMNS = arrayOf(COLUMN_ID, COLUMN_FK_USER, COLUMN_DATE, COLUMN_LEVEL)
    }
}