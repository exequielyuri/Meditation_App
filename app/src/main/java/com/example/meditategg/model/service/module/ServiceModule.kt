package com.example.meditategg.model.service.module

import com.example.meditategg.model.service.AccountService
import com.example.meditategg.model.service.LogService
import com.example.meditategg.model.service.StorageService
import com.example.meditategg.model.service.impl.AccountServiceImpl
import com.example.meditategg.model.service.impl.LogServiceImpl
import com.example.meditategg.model.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
}