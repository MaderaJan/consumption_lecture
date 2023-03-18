package cz.muni.consumption.repository

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class ConsumptionProcessor {

    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    fun onProcessFinished(bitmap: Bitmap?, onResult: (List<Int>) -> Unit) {
        if (bitmap == null) {
            onResult(emptyList())
            return
        }

        val inputImage = InputImage.fromBitmap(bitmap, 0)

        recognizer.process(inputImage)
            .addOnSuccessListener {
                val consumptionCandidates = parseConsumptionFromTextBlocks(it)
                onResult(consumptionCandidates)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }

    private fun parseConsumptionFromTextBlocks(resultText: Text?): List<Int> {
        if (resultText == null) return emptyList()

        val consumptionCandidates = mutableListOf<Int>()

        for (block in resultText.textBlocks) {
            val candidate = block.text.replace(" ", "").toIntOrNull()
            if (candidate != null) {
                consumptionCandidates.add(candidate)
            }
        }

        return consumptionCandidates
    }
}