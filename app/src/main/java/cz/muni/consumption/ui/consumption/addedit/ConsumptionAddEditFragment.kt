package cz.muni.consumption.ui.consumption.addedit

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import cz.muni.consumption.R
import cz.muni.consumption.data.ConsumptionType
import cz.muni.consumption.databinding.FragmentConsuptionAddEditBinding
import cz.muni.consumption.repository.ConsumptionProcessor
import cz.muni.consumption.repository.ConsumptionRepository
import cz.muni.consumption.util.DateUtil
import java.util.*

class ConsumptionAddEditFragment : Fragment() {

    private lateinit var binding: FragmentConsuptionAddEditBinding

    private val args: ConsumptionAddEditFragmentArgs by navArgs()
    private val consumptionRepository: ConsumptionRepository by lazy {
        ConsumptionRepository(requireContext())
    }

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

        binding.saveButton.setOnClickListener {
            val consumptionText = binding.consumptionEditText.text.toString()
            val dateText = binding.dateEditText.text.toString()

            if (isConsumptionFormValid(consumptionText, dateText)) {
                val consumption = consumptionText.toDouble()
                val date = DateUtil.dateFormat.parse(dateText)!!
                val type = when (binding.consumptionTypeChipGroup.checkedChipId) {
                    R.id.gas_chip -> ConsumptionType.GAS
                    else -> ConsumptionType.ELECTRICITY
                }

                consumptionRepository.saveOrUpdate(
                    id = args.measuredConsumption?.id,
                    consumption = consumption,
                    date = date,
                    type = type,
                )
                findNavController().navigateUp()
            }
        }
    }

    // TODO 1.2 Init Values
    private fun setInitialValues() {
        val measuredConsumption = args.measuredConsumption

        if (measuredConsumption == null) {
            setInitDate(null)
            setInitConsumptionCandidateValues(args.measurementBitmap)
        } else {
            setInitDate(measuredConsumption.measurementDate)
            binding.consumptionEditText.setText(measuredConsumption.consumption.toString())
            when (measuredConsumption.type) {
                ConsumptionType.ELECTRICITY -> binding.consumptionTypeChipGroup.check(R.id.electricity_chip)
                ConsumptionType.GAS -> binding.consumptionTypeChipGroup.check(R.id.gas_chip)
            }
        }
    }

    private fun setInitConsumptionCandidateValues(measurementBitmap: Bitmap?) {
        if (measurementBitmap == null) return

        val processor = ConsumptionProcessor()
        processor.onProcessFinished(measurementBitmap) { candidatesValues ->
            candidatesValues.forEach { candidate ->
                val chip = Chip(context)
                chip.text = candidate.toString()
                binding.consumptionCandidatesChipGroup.addView(chip)

                chip.setOnClickListener {
                    binding.consumptionEditText.setText(candidate.toString())
                }
            }
        }
    }

    private fun setInitDate(date: Date?) {
        val dateText = DateUtil.dateFormat.format(date ?: Date(System.currentTimeMillis()))
        binding.dateEditText.setText(dateText)
    }

    // TODO 1.1 Validace
    private fun isConsumptionFormValid(consumption: String, date: String): Boolean {
        if (consumption.isEmpty() || consumption.toDoubleOrNull() === null) {
            binding.consumptionEditText.error = getString(R.string.consumption_add_edit_field_is_not_valid)
            return false
        }

        try {
            if (DateUtil.dateFormat.parse(date) == null) {
                return false
            }
        } catch (ex: Exception) {
            binding.dateEditText.error = getString(R.string.consumption_add_edit_field_is_not_valid)
            return false
        }

        return true
    }
}