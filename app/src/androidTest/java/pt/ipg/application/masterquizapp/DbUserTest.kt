package pt.ipg.application.masterquizapp

import android.database.sqlite.SQLiteDatabase
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import pt.ipg.application.masterquizapp.database.`object`.User
import pt.ipg.application.masterquizapp.database.DBTable
import pt.ipg.application.masterquizapp.database.QuizDBOpenHelper
import pt.ipg.application.masterquizapp.database.table.UsersTable

@RunWith(AndroidJUnit4::class)
class DbUserTest {
    private fun getContext() = InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase {
        val bdOpenHelper = QuizDBOpenHelper(getContext())
        return bdOpenHelper.writableDatabase
    }

    private fun insertUser(tableUsers: UsersTable, user: User) : Long {
        val id = tableUsers.insert(user.toContentValues())
        assertNotEquals(-1, id)
        return id
    }

    private fun getUserDatabase(tableUsers: UsersTable, id: Long): User {
        val cursor = tableUsers.query(
            UsersTable.ALL_COLUMNS,
            "${UsersTable.TABLE_NAME}.${DBTable.COLUMN_ID}=?",
            arrayOf("$id"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        return User.fromCursor(cursor);
    }

    @Before
    fun deleteDatabase() {
        getContext().deleteDatabase(QuizDBOpenHelper.DATABASE_NAME)
    }

    @Test
    fun canCreateAndOpenDatabase() {
        val bdOpenHelper = QuizDBOpenHelper(getContext())
        val db = bdOpenHelper.readableDatabase
        assert(db.isOpen)
        db.close()
    }

    @Test
    fun canInsertUser() {
        val db = getWritableDatabase()
        insertUser(UsersTable(db), User("Paulo Marques", "Portugal", "04-02-2021 20:12"))
        db.close()
    }

    @Test
    fun canDeleteUser() {
        val db = getWritableDatabase()
        val tableUsers = UsersTable(db)

        val id = insertUser(tableUsers, User("Anonymous", "United States of America", "00-00-0000 00:00"))
        val numRecordsDeleted = tableUsers.delete(id)
        assertEquals(1, numRecordsDeleted)

        db.close()
    }

    @Test
    fun canUpdateUser() {
        val db = getWritableDatabase()
        val tableUsers = UsersTable(db)

        val user = User("Anonymous", "United States of America", "00-00-0000 00:00")
        user.id = insertUser(tableUsers, user)

        val numRecordsUpdated = tableUsers.update(user.id, user.toContentValues())
        assertEquals(1, numRecordsUpdated)
        assertEquals(user, getUserDatabase(tableUsers, user.id))

        db.close()
    }

    @Test
    fun canReadUser() {
        val db = getWritableDatabase()
        val tableUsers = UsersTable(db)

        val user = User("Anonymous", "United States of America", "00-00-0000 00:00")
        user.id = insertUser(tableUsers, user)

        assertEquals(user, getUserDatabase(tableUsers, user.id))
    }
}