package cz.muni.consumption.ui.consumption

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cz.muni.consumption.R
import cz.muni.consumption.databinding.FragmentConsumptionBinding

class ConsumptionFragment : Fragment() {

    // TODO 8. ListFragment binding
    private lateinit var binding: FragmentConsumptionBinding

    // TODO 9. onCreateView && Jiné vytvoření View pro Fragment jak pro Activitu
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentConsumptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}