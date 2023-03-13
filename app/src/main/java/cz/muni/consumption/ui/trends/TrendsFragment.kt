package cz.muni.consumption.ui.trends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.consumption.databinding.FragmentTrendsBinding
import cz.muni.consumption.ui.repository.TrendRepository
import cz.muni.consumption.ui.data.ConsumptionType

// TODO 2. (S) - Vytvoření Trend obrazovky s 2 horizontal recyclery viz prezentace

// TODO Hint: Step by Step
// TODO 1. Vytvořit layout s recycler views viz prezentace -> fragment_trends.xml
// TODO 2. Vytvořit layout -> item_trend.xml
// TODO 3. Vytvořit ListAdapter, ViewHolder, DiffUtil v -> TrendsAdapter.kt
// TODO 4. Propojit adapter, recycler a data z TrendRepository.kt
class TrendsFragment : Fragment() {

    private lateinit var binding: FragmentTrendsBinding

    private val trendRepository: TrendRepository by lazy {
        TrendRepository()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTrendsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        var trendData = trendRepository.getMockedTrends(ConsumptionType.ELECTRICITY)
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // TODO Hint: check TrendRepository
        // TODO Hint: Horizontal recycler -> LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        // TODO Hint: adapter.submitList(data) s použitím TrendRepository
    }
}