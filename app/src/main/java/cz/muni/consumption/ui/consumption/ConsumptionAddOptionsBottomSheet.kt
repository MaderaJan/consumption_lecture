package cz.muni.consumption.ui.consumption

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import cz.muni.consumption.databinding.BottomSheetConsumtionAddOptionsBinding
import cz.muni.consumption.repository.ConsumptionDataGenerator
import cz.muni.consumption.repository.ConsumptionRepository

// TODO 2.1 Představení ConsumptionAddOptionsBottomSheet -> Photo, Gallery, Generate
class ConsumptionAddOptionsBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetConsumtionAddOptionsBinding
    private val consumptionRepository: ConsumptionRepository by lazy {
        ConsumptionRepository(requireContext())
    }

    // TODO 2.2 take photo launcher
    private val takePhotoLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            processPhotoResult(it)
        }

    // TODO 2.3 gallery launcher
    private val fromGalleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            processFromGalleryResult(it)
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomSheetConsumtionAddOptionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO 2.4 Zahájení focení
        binding.takePhotoTextView.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePhotoLauncher.launch(intent)
        }

        // TODO 2.5 Zahájení galerie
        binding.fromGalleryTextView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            fromGalleryLauncher.launch(intent)
        }

        binding.manualTextView.setOnClickListener {
            navigateToAddEditFragment(image = null)
        }

        // TODO 2.6 Generátor dat
        binding.generateTextView.setOnClickListener {
            val data = ConsumptionDataGenerator().generateData()
            consumptionRepository.saveAll(data)
            findNavController().navigateUp()
        }
    }

    // TODO 2.2 processPhotoResult
    private fun processPhotoResult(activityResult: ActivityResult) {
        if (activityResult.resultCode == AppCompatActivity.RESULT_OK) {
            val image = activityResult.data?.extras?.get("data") as? Bitmap
            navigateToAddEditFragment(image)
        }
    }

    // TODO 2.3 processFromGalleryResult
    private fun processFromGalleryResult(activityResult: ActivityResult) {
        if (activityResult.resultCode == AppCompatActivity.RESULT_OK) {
            val uri = activityResult.data?.data
            val image = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            navigateToAddEditFragment(image)
        }
    }

    private fun navigateToAddEditFragment(image: Bitmap?) {
        //  TODO (S) Navigate to ConsumptionAddEdit
    }
}
