package pt.ipg.application.masterquizapp

import android.database.sqlite.SQLiteDatabase

class UserChoiceTable(db : SQLiteDatabase) : DBTable(db, TABLE_NAME) {
    fun create() {
        db.execSQL("CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_FK_USER INTEGER NOT NULL, $COLUMN_FK_CHOICE INTEGER )")
    }

    companion object {
        const val TABLE_NAME = "user_choice"
        const val COLUMN_FK_USER = "fk_user"
        const val COLUMN_FK_CHOICE = "fk_choice"

        val ALL_COLUMNS = arrayOf(COLUMN_ID, COLUMN_FK_USER, COLUMN_FK_CHOICE)
    }
}