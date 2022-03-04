package pt.ipg.application.masterquizapp.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

open class DBTable(db : SQLiteDatabase, tableName: String) {
    protected val db = db
    protected val tableName = tableName

    fun insert(values: ContentValues) = db.insert(tableName, null, values)

    fun update(id: Long, values: ContentValues) =
        db.update(tableName, values, "$COLUMN_ID=?", arrayOf("$id"))

    fun delete(id: Long) =
        db.delete(tableName, "$COLUMN_ID=?", arrayOf("$id"))

    open fun query(columns: Array<String>, selection: String?, selectionArgs: Array<String>?, groupBy: String?, having: String?, orderBy: String?) =
        db.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy)

    companion object {
        const val COLUMN_ID = BaseColumns._ID
    }
}