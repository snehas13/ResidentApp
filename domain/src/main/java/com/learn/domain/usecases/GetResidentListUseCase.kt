package com.learn.domain.usecases

import com.learn.domain.entities.Resident
import com.learn.domain.entities.ResidentRepository
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.get

class GetResidentListUseCase : SingleUseCase<List<Resident>, GetResidentListUseCase.Params>(), KoinComponent {

    val repository : ResidentRepository = get()

    override fun buildUseCaseSingle(params: Params): Single<List<Resident>> {
        return with(params) {
            repository.getResidentList()
        }
    }

    class Params

}