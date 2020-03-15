package com.learn.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.learn.domain.entities.Resident
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface ResidentDao {

    @Query("select * from Resident")
    fun getResidentList() : Maybe<List<Resident>>

    @Insert
    fun addResident(resident : Resident) :  Single<Long>

    @Update
    fun editResident(resident : Resident) : Single<Int>
}