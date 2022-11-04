package com.example.meditategg.model.service.impl

import com.example.meditategg.model.service.LogService
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class LogServiceImpl @Inject constructor() : LogService {
    override fun logNonFatalCrash(throwable: Throwable?) {
        if (throwable != null) Firebase.crashlytics.recordException(throwable)
    }
}