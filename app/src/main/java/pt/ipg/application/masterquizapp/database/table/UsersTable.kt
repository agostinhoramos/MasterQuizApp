package pt.ipg.application.masterquizapp.database.table

import android.database.sqlite.SQLiteDatabase
import pt.ipg.application.masterquizapp.database.DBTable

class UsersTable(db : SQLiteDatabase) : DBTable(db, TABLE_NAME) {
    fun create() {
        db.execSQL("CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT NOT NULL, $COLUMN_COUNTRY TEXT, $COLUMN_CREATED_AT TEXT NOT NULL)")
    }

    companion object {
        const val TABLE_NAME = "user"
        const val COLUMN_NAME = "name"
        const val COLUMN_COUNTRY = "country"
        const val COLUMN_CREATED_AT = "created_at"

        val ALL_COLUMNS = arrayOf(COLUMN_ID, COLUMN_NAME, COLUMN_COUNTRY, COLUMN_CREATED_AT)
    }
}