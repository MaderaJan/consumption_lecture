package cz.muni.consumption.ui.consumption.addedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import cz.muni.consumption.databinding.FragmentConsuptionAddEditBinding
import cz.muni.consumption.util.DateUtil
import java.util.*

// TODO 3.1 Projít ConsumptionRepository + Consumption Entity, Database, Dao, Mapper
// TODO 4.1 Představení obrazovky
// TODO 4.1 Představení samostatné práce
// - Dokončit UI pro ConsumptionAddEditFragment
// - Zprovoznit vytvoření Consumption, které bude uložné do databáze
// - ConsumptionFragment zobarzí data z databáze
class ConsumptionAddEditFragment : Fragment() {

    private lateinit var binding: FragmentConsuptionAddEditBinding

    // TODO 2.1 safe args
//    private val args: ConsumptionAddEditFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentConsuptionAddEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInitialValues()

        binding.dateTextInputLayout.setEndIconOnClickListener {
            val datePicker = MaterialDatePicker.Builder
                .datePicker()
                .build()

            datePicker
                .addOnPositiveButtonClickListener {
                    val dateText = DateUtil.dateFormat.format(Date(it))
                    binding.dateEditText.setText(dateText)
                }

            datePicker.show(parentFragmentManager, "DATE_PICKER")
        }

        // TODO 5. (S) Validace po kliku na save
        // HINT: pro validaci použít isConsumptionFormValid
        // TODO 5. (S) Uložení do DB
        // HINT: ConsumptionRepository.saveOrUpdate
        // TODO 5. (S) Návrat na obrazovku ConsumptionFragment
        // HINT: navigace o obrazoku zpět findNavController().navigateUp()
    }

    // TODO 2.2 setInitialValues
    private fun setInitialValues() {
        /*val measuredConsumption = args.measuredConsumption

        if (measuredConsumption == null) {
            setInitDate(null)
        } else {
            // TODO 6. (S) Editace
        }*/
    }

    private fun setInitDate(date: Date?) {
        val dateText = DateUtil.dateFormat.format(date ?: Date(System.currentTimeMillis()))
        binding.dateEditText.setText(dateText)
    }

    // TODO 5. (S) validace consumption & datumu
    // HINT: binding.dateEditText.error = getString(R.string.consumption_add_edit_field_is_not_valid)
    private fun isConsumptionFormValid(consumption: String, date: String): Boolean =
        false
}