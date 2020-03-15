package com.learn.residentapp.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.learn.domain.entities.Resident
import com.learn.domain.usecases.AddResidentUseCase
import com.learn.domain.usecases.EditResidentUseCase
import com.learn.residentapp.R
import com.learn.residentapp.Utility
import com.learn.residentapp.Utility.ADD
import com.learn.residentapp.Utility.EDIT

class ResidentViewModel(application: Application) : AndroidViewModel(application) {

    var id : Long = 0
    var name :  String = ""
    var age : Int = 0
    var birthDate :  String = ""
    private val context = getApplication<Application>().applicationContext


    fun setResident(resident: Resident) {
        Log.i("ResidentViewModel","Setting resident ${resident.id} ${resident.name} ${resident.age} ${resident.birthDate}")
        id = resident.id
        name = resident.name
        age = resident.age
        birthDate = Utility.dateToString(resident.birthDate)
    }


    fun onSaveClicked(action : String?) {
        Log.i("ResidentViewModel","onSaveClicked action $action")
        if(action  == ADD) {
            addNewResident()
        } else if(action ==  EDIT) {
            updateResident()
        }
    }

    fun addNewResident() {
      if(name.isNotEmpty() && birthDate.isNotEmpty()) {
          var resident = Resident(0,name,age,Utility.stringToDate(birthDate))
          Log.i("ResidentViewModel","adding ${resident.id} ${resident.age} ${resident.name} ${resident.birthDate}")
          AddResidentUseCase().execute(
              onSuccess = {
                  Log.i("ViewModel","Successfully inserted  record with id $it")
            },
            onError = { it.printStackTrace() },
            params = AddResidentUseCase.Params(resident)
         )
      } else {
          Toast.makeText(context,context.getString(R.string.validation),Toast.LENGTH_LONG).show()
      }
    }

    fun updateResident() {
        if(name.isNotEmpty() && birthDate.isNotEmpty()) {
            var resident = Resident(id,name,age,Utility.stringToDate(birthDate))
            Log.i("ResidentViewModel","editing ${resident.id} ${resident.age} ${resident.name} ${resident.birthDate}")
            EditResidentUseCase().execute(
                onSuccess = {
                    Log.i("ViewModel","Successfully updated the record with id $it")
                },
                onError = { it.printStackTrace() },
                params = EditResidentUseCase.Params(resident)
            )
        } else {
            Toast.makeText(context,context.getString(R.string.validation),Toast.LENGTH_LONG).show()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}