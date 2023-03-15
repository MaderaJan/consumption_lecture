package cz.muni.consumption.ui.consumption

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.consumption.databinding.FragmentConsumptionBinding
import cz.muni.consumption.repository.ConsumptionRepository

class ConsumptionFragment : Fragment() {

    private lateinit var binding: FragmentConsumptionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentConsumptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private val consumptionRepository: ConsumptionRepository by lazy {
        ConsumptionRepository(requireContext())
    }

    private val adapter: ConsumptionAdapter by lazy {
        ConsumptionAdapter(
            onItemClick = { consumption ->
                findNavController()
                    .navigate(ConsumptionFragmentDirections.actionConsumptionFragmentToConsumptionAddEditFragment(consumption))
            },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.addConsumptionButton.setOnClickListener {
            findNavController()
                .navigate(
                    ConsumptionFragmentDirections.actionConsumptionFragmentToConsumptionAddEditFragment(
                        measuredConsumption = null
                    )
                )
        }
    }

    private fun refreshList() {
        adapter.submitList(consumptionRepository.getAllMeasuredConsumption())
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }
}