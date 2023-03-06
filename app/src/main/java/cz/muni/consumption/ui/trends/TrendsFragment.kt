package cz.muni.consumption.ui.trends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    override fun onStart() {
        super.onStart()
    }

    private val adapter: TrendsAdapter by lazy {
        TrendsAdapter(
            onItemClick = { consumption ->
                Toast.makeText(requireContext(), consumption.consumption.toString(), Toast.LENGTH_SHORT)
                    .show()
            },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gasRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.electricityRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.gasRecycler.adapter = adapter
        binding.electricityRecycler.adapter = adapter
    }

    private fun refreshList() {
        adapter.submitList(trendRepository.getMockedTrends(ConsumptionType.GAS))
        adapter.submitList(trendRepository.getMockedTrends(ConsumptionType.ELECTRICITY))
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }
}