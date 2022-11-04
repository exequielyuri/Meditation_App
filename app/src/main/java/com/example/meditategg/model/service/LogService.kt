package com.example.meditategg.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable?)
}