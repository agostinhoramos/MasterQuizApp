package pt.ipg.application.masterquizapp.database.table

import android.database.sqlite.SQLiteDatabase
import pt.ipg.application.masterquizapp.database.DBTable

class ChoiceTable(db : SQLiteDatabase) : DBTable(db, TABLE_NAME) {
    fun create() {
        db.execSQL("CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_FK_QUESTION INTEGER NOT NULL, $COLUMN_CHOICE TEXT)")
    }

    companion object {
        const val TABLE_NAME = "choice"
        const val COLUMN_FK_QUESTION = "fk_question"
        const val COLUMN_CHOICE = "choice"

        val ALL_COLUMNS = arrayOf(COLUMN_ID, COLUMN_FK_QUESTION, COLUMN_CHOICE)
    }
}