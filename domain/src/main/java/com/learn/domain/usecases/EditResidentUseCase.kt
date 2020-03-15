package com.learn.domain.usecases

import com.learn.domain.entities.Resident
import com.learn.domain.entities.ResidentRepository
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import org.koin.standalone.inject

class EditResidentUseCase : SingleUseCase<Int,EditResidentUseCase.Params>(),KoinComponent {

    val repository : ResidentRepository = get()

    override fun buildUseCaseSingle(params: Params): Single<Int> {
        return with(params) {
            repository.editResident(params.resident)
        }
    }

    class Params(val resident: Resident)
}