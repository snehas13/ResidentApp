package com.learn.data.repositories

import android.content.Context
import com.learn.data.db.ResidentDatabase
import com.learn.domain.entities.Resident
import com.learn.domain.entities.ResidentRepository
import io.reactivex.Single

class ResidentRepositoryImpl(context: Context) : ResidentRepository {

    private val residentDao = ResidentDatabase.getDatabase(context)!!.residentDao()

    override fun getResidentList(): Single<List<Resident>> {
        return residentDao.getResidentList().toSingle()
    }

    override fun editResident(resident: Resident): Single<Int> {
        return residentDao.editResident(resident)
    }

    override fun addResident(resident: Resident): Single<Long> {
        return residentDao.addResident(resident)
    }
}
