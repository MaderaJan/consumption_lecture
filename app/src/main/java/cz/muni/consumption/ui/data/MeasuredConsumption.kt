package cz.muni.consumption.ui.data

import android.os.Parcelable
import cz.muni.consumption.ui.util.oneDecimal
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class MeasuredConsumption(
    val id: Long,
    val consumption: Double,
    val measurementDate: Date,
    val type: ConsumptionType
) : Parcelable {

    fun getConsumptionText(): String =
        "${this.consumption.oneDecimal()} ${this.type.getUnit()}"
}