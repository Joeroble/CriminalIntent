package com.joeroble.android.criminalintent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment

class CrimeFragment : Fragment() {

    // establishes the components before being initialized(buttons, edittext, etc.)
    private lateinit var  crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    // in the onCreate, crime will be established as a crime data class.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
    }

    // in onCreateView, the titleField, dateButton, and solvedCheckBox are initialized.  The
    // dateButton has a static string attached to it, and is set as false for the time being. The inflate
    // is also stored in view, which will then be inflated after the components are initialized and
    // view is returned.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime, container, false)

//        initializes the ui components
        titleField = view.findViewById(R.id.crime_title) as EditText
        dateButton = view.findViewById(R.id.crime_date) as Button
        solvedCheckBox = view.findViewById(R.id.crime_solved) as CheckBox

        // Sets the dateButton to a string, and is currently disabled.
        dateButton.apply{
            text = crime.date.toString()
            isEnabled = false
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        // sets up the components for titleField, this will monitor the text being entered
        val titleWatcher = object: TextWatcher {
            override fun beforeTextChanged(sequence: CharSequence?,
                                           start: Int,
                                           before: Int,
                                           count: Int) {
                // this space intentially left blank
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                crime.title = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {
                // this one too
            }
        }
        titleField.addTextChangedListener(titleWatcher)

        // sets up the solvedCheckBox which will be a boolean
        solvedCheckBox.apply {
            setOnCheckedChangeListener{_, isChecked ->
                crime.isSolved = isChecked
            }
        }
    }
}