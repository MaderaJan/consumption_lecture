package cz.muni.consumption.ui.data

import android.os.Parcelable
import cz.muni.consumption.ui.util.oneDecimal
import kotlinx.parcelize.Parcelize
import java.util.*

// TODO 3.1 Data layer
// TODO 3.2 Parcelize & Parcelable
@Parcelize
data class MeasuredConsumption(
    val id: Long,
    val consumption: Double,
    val measurementDate: Date,
    val type: ConsumptionType
) : Parcelable {

    // TODO 4.1 oneDecimal extension
    fun getConsumptionText(): String =
        "${this.consumption.oneDecimal()} ${this.type.getUnit()}"
}