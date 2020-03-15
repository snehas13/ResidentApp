package com.learn.residentapp.di

import com.learn.data.repositories.ResidentRepositoryImpl
import com.learn.domain.entities.ResidentRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module


val appModule = module(override = true) {
    single<ResidentRepository>{
        ResidentRepositoryImpl(androidContext())
    }
}