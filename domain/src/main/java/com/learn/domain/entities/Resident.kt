package com.learn.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Entity(tableName = "Resident")
@Parcelize
data class Resident(
    @PrimaryKey(autoGenerate = true) var id : Long,
    var name : String,
    var age : Int,
    var birthDate : Date): Parcelable