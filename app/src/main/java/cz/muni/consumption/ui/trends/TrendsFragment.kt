package cz.muni.consumption.ui.trends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cz.muni.consumption.databinding.FragmentTrendsBinding
import java.util.*

class TrendsFragment : Fragment() {

    private lateinit var binding: FragmentTrendsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTrendsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.tlacitko.setOnClickListener {
            val c = Calendar.getInstance()
            binding.currentDateTextView.text = "${System.currentTimeMillis()}, " + binding.currentDateTextView.text
        }
    }
}