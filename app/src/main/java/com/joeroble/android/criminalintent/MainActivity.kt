package com.joeroble.android.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // stores the fragment container in currentFragment
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        // if the container is empty/null, it will create a new fragment from CrimeFragment()
        // and then call on supportFragmentManager to pass it the fragment and inflate it.
        if (currentFragment == null){

            val fragment = CrimeFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }


    }
}