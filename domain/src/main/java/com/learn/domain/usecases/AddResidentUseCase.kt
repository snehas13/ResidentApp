package com.learn.domain.usecases

import com.learn.domain.entities.Resident
import com.learn.domain.entities.ResidentRepository
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.get

class AddResidentUseCase : SingleUseCase<Long, AddResidentUseCase.Params>(),KoinComponent {

    val repository : ResidentRepository = get()

    override fun buildUseCaseSingle(params: Params): Single<Long> {

        return with(params) {
            repository.addResident(params.resident)
        }
    }


    class Params(val resident: Resident)
}