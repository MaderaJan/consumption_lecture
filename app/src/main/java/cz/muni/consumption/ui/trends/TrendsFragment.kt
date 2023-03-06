package cz.muni.consumption.ui.trends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.consumption.databinding.FragmentTrendsBinding
import cz.muni.consumption.ui.data.ConsumptionType
import cz.muni.consumption.ui.repository.TrendRepository

class TrendsFragment : Fragment() {

    private lateinit var binding: FragmentTrendsBinding

    private val trendRepository: TrendRepository by lazy {
        TrendRepository()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTrendsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        electricityAdapter.submitList(trendRepository.getMockedTrends(ConsumptionType.ELECTRICITY))
        gasAdapter.submitList(trendRepository.getMockedTrends(ConsumptionType.GAS))
    }

}