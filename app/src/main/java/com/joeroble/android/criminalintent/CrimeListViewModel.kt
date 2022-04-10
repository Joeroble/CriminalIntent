package com.joeroble.android.criminalintent

import androidx.lifecycle.ViewModel


// sets up the CrimeListViewModel - pulls in the CrimeRepository, and then gets all the crimes
// using the .getCrimes() and saves it in crimeListLiveData, which will be used in the fragment
// to populate the crimes.
class CrimeListViewModel: ViewModel() {

    private val crimeRepository = CrimeRepository.get()
    val crimeListLiveData = crimeRepository.getCrimes()

    fun addCrime(crime: Crime){
        crimeRepository.addCrime(crime)
    }


}