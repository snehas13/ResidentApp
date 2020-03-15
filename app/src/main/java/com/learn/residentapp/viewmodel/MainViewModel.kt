package com.learn.residentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.learn.domain.entities.Resident
import com.learn.domain.usecases.GetResidentListUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val residentLiveData: MutableLiveData<List<Resident>> = MutableLiveData()

    fun getAllResidents() {
        GetResidentListUseCase().execute(
            onSuccess = {
                Log.i("ViewModel","Successfully updated the list with length ${it.size}")
                residentLiveData.postValue(it)

            },
            onError = { it.printStackTrace() },
            params = GetResidentListUseCase.Params()
        )
    }

    override fun onCleared() {
        super.onCleared()
    }
}