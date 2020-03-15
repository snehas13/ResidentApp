package com.learn.domain.entities

import io.reactivex.Single

interface ResidentRepository {

    fun getResidentList() : Single<List<Resident>>
    fun editResident(resident: Resident) : Single<Int>
    fun addResident(resident: Resident) : Single<Long>

}