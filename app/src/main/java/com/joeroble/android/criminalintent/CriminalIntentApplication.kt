package com.joeroble.android.criminalintent

import android.app.Application
// initializes the CrimeRepository on application boot, this is also the app id in the AndroidManifest.
class CriminalIntentApplication: Application() {

    override fun onCreate(){
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}