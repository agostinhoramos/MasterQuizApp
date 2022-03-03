package pt.ipg.application.masterquizapp

import android.database.sqlite.SQLiteDatabase
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DbUsersTest {
    private fun getContext() = InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase {
        val bdOpenHelper = QuizDBOpenHelper(getContext())
        return bdOpenHelper.writableDatabase
    }

    private fun insertUser(tableUsers: TableUsers, user: User) : Long {
        val id = tableUsers.insert(user.toContentValues())
        assertNotEquals(-1, id)
        return id
    }
}