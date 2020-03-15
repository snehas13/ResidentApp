package com.learn.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.learn.domain.entities.Resident

@Database(
    entities = [Resident::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DBTypeConverters::class)
abstract class ResidentDatabase : RoomDatabase() {

    abstract fun residentDao() : ResidentDao

    companion object {

        private var instance : ResidentDatabase?= null

        private const val DB_NAME = "resident.db"

        fun getDatabase(context: Context) : ResidentDatabase? {

            if(instance == null) {
                instance = Room.databaseBuilder(context, ResidentDatabase::class.java, DB_NAME).build()
            }
            return instance
        }

    }
}