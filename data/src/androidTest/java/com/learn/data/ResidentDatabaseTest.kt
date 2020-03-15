package com.learn.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.learn.data.db.ResidentDao
import com.learn.data.db.ResidentDatabase
import com.learn.domain.entities.Resident
import org.junit.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import java.text.SimpleDateFormat
import java.util.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class ResidentDatabaseTest {

    private lateinit var database: ResidentDatabase
    private lateinit var residentDao: ResidentDao

    private var df: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

    var id : Long = 0

    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        try {
            database = Room.inMemoryDatabaseBuilder(context, ResidentDatabase::class.java)
                .allowMainThreadQueries().build()
        } catch (e: Exception) {
            Log.i("ResidentDatabaseTest", e.message)
        }
        residentDao = database.residentDao()

    }

    @Test
    fun testDatabaseEntry() {
        var status : Boolean = false
        var resident = Resident(0,"test",9, df.parse("2010-12-03"))
        residentDao.addResident(resident).subscribe(
            {
                    v -> println("Value is: $v")
                    id = v
                    status = true
            },
            {
                    e -> println("Error: $e")
            })
        assertTrue(status)

        residentDao.getResidentList().subscribe(
            {
                    v -> println("Value is: $v")
                    assertTrue(v.isNotEmpty())

                    for(resident in v) {
                        if(resident.id == id) {
                            status = true
                        }
                    }
            },
            {
                    e -> println("Error: $e")
            })
        assertTrue(status)

        resident = Resident(id,"testing",9, df.parse("2010-12-03"))
        residentDao.editResident(resident).subscribe(
            {
                    v -> println("Value is: $v")
                    status = true

            },
            {
                    e -> println("Error: $e")
            })
        assertTrue(status)

        residentDao.getResidentList().subscribe(
            {
                    v -> println("Value is: $v")
                    status = true
                    assertTrue(v.isNotEmpty())

                    for(resident in v) {
                        if(resident.id == id && resident.name == "testing") {
                        status = true
                        }
                    }
            },
            {
                    e -> println("Error: $e")
            })
        assertTrue(status)
    }

    @After
    fun tearDown() {
        database.close()
    }
}