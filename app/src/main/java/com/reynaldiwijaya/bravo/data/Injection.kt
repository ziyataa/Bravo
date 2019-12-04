package com.reynaldiwijaya.bravo.data

import com.reynaldiwijaya.bravo.data.local.LocalDataSource
import com.reynaldiwijaya.bravo.data.remote.RemoteDataSource

object Injection {
    fun provideApiRepository(): ApiRepository {
        return ApiRepository(RemoteDataSource, LocalDataSource)
    }
}