package cz.muni.consumption.ui.trends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.consumption.databinding.FragmentTrendsBinding
import cz.muni.consumption.repository.TrendRepository

class TrendsFragment : Fragment() {

    private lateinit var binding: FragmentTrendsBinding

    private val trendRepository: TrendRepository by lazy {
        TrendRepository(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTrendsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.electricityRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val electricityAdapter = TrendAdapter()
        binding.electricityRecycler.adapter = electricityAdapter

        binding.gasRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val gasAdapter = TrendAdapter()
        binding.gasRecycler.adapter = gasAdapter

        trendRepository.getThisYearTrends(
            success = { electricity, gas ->
                electricityAdapter.submitList(electricity)
                gasAdapter.submitList(gas)
            },
            fail = {
                Toast.makeText(context, "Server error", Toast.LENGTH_SHORT).show()
            }
        )
    }

}