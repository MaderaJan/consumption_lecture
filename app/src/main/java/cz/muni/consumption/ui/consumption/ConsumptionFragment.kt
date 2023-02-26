package cz.muni.consumption.ui.consumption

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cz.muni.consumption.databinding.FragmentConsumptionBinding
import cz.muni.consumption.ui.repository.ConsumptionRepository

class ConsumptionFragment : Fragment() {

    private lateinit var binding: FragmentConsumptionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentConsumptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    // TODO 10.2 ConsumptionRepository
    private val consumptionRepository: ConsumptionRepository by lazy {
        ConsumptionRepository()
    }

    private val adapter: ConsumptionAdapter by lazy {
        ConsumptionAdapter(
            onItemClick = { consumption ->
                Toast.makeText(requireContext(), consumption.getConsumptionText(), Toast.LENGTH_SHORT)
            },
        )
    }

    // TODO 8. onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO 9. recyclerView -> layout manager + adapter
//        binding.recyclerView.layoutManager = LinearLayoutManager(context)
//        binding.recyclerView.adapter = adapter
    }

    private fun refreshList() {
        adapter.submitList(consumptionRepository.getAllMeasuredConsumption())
    }

    // TODO 10.1 onResume
    override fun onResume() {
        super.onResume()
        refreshList()
    }
}