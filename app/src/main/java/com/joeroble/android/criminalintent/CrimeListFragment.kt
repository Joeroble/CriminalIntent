package com.joeroble.android.criminalintent


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [CrimeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

//Tag created for debugging in logcat.
private const val TAG = "CrimeListFragment"

class CrimeListFragment : Fragment() {
    //**
    //* Required interface for hosting activities
    interface Callbacks{
        fun onCrimeSelected(crimeId: UUID)
    }

    private var callbacks: Callbacks? = null

    //This sets up the RecyclerView that will be initialized later, and sets up the crimeListViewModel
    // using lazy initialization.
    private lateinit var crimeRecyclerView: RecyclerView
    private var adapter: CrimeAdapter? = CrimeAdapter(emptyList())

    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProviders.of(this).get(CrimeListViewModel::class.java)
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        callbacks = context as Callbacks?
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)

        // Initializes the RecyclerView, and the layout which is using a LinearLayout in this instance.
        crimeRecyclerView=
            view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)
        crimeRecyclerView.adapter = adapter

        return view
    }


    //Overrides the view when its created, and looks at the live data of the crimeListViewModel with
    // observe, and passes the crimes to the updateUI function.  It also displays a message in logcat
    // for the number of crimes found.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        crimeListViewModel.crimeListLiveData.observe(
            viewLifecycleOwner,
            Observer{crimes ->
                crimes?.let{
                    Log.i(TAG, "Got crimes ${crimes.size}")
                    updateUI(crimes)
                }

            }
        )
    }

    override fun onDetach(){
        super.onDetach()
        callbacks = null
    }

    // The updateUI function pulls in the list of crimes from the crimeListViewModel, passes it
    // to the CrimeAdapter, which sorts out the data, and then passes it to the RecyclerView adapter.
    private fun updateUI(crimes: List<Crime>){
        val adapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = adapter
    }

    // Sets up the CrimeHolder inner class - this initializes and wires up the title and date text
    // views, and the solvedImageView that only appears on solved crimes.  This entire class
    // is for the display and binding of each crime in the RecyclerView.  In the bind, it sets up
    // the text for the title and date, and checks if the crime is solved, if it is it will display
    // the image, if not it will not display the image.  onClick has been setup, and now crimes
    // can be accessed.
    private inner class CrimeHolder(view: View):
        RecyclerView.ViewHolder(view), View.OnClickListener{

        private lateinit var crime: Crime

        private val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
        private val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind (crime: Crime){
            this.crime = crime
            titleTextView.text = this.crime.title
            dateTextView.text = this.crime.date.toString()
            solvedImageView.visibility = if (crime.isSolved){
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        override fun onClick(v: View){
            callbacks?.onCrimeSelected(crime.id)
        }
    }

    // This sets up the inner class for the CrimeAdapter, this is what takes the display
    // configurations of the CrimeHolder, and binds them to a position in the RecyclerView for
    // display to the user.
    private inner class CrimeAdapter(var crimes: List<Crime>)
        : RecyclerView.Adapter<CrimeHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            return CrimeHolder(view)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            holder.bind(crime)
        }

        override fun getItemCount() = crimes.size

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         */
        @JvmStatic
        fun newInstance(): CrimeListFragment {
                    return CrimeListFragment()
                }
            }
    }
