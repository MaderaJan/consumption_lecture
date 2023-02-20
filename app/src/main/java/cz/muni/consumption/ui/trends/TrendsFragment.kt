package cz.muni.consumption.ui.trends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cz.muni.consumption.R
import cz.muni.consumption.databinding.FragmentConsumptionBinding
import cz.muni.consumption.databinding.FragmentTrendsBinding

// TODO 11. (S) Vytvoření bidning pro trends fragment
class TrendsFragment : Fragment() {

    private lateinit var binding: FragmentTrendsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.addTimeButton.setOnClickListener {
            var time = System.currentTimeMillis()
            binding.currentDateTextView.append(time.toString())
        }

    }

}

// TODO 14. (S) napsat funkci, která
// TODO - po klikud na tlačtíko -> button.setOnClickListener { }
// TODO - veme text z TextView -> current_date_text_view
// TODO - Získá aktuální čas
// TODO - a spojí předchozí text se získaným časem
// System.currentTimeMillis() // Android
// Calendar.getInstance()   // Java