package cz.muni.consumption.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO 3.4 DB
@Entity
data class MeasuredConsumptionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val consumption: Double,
    val measurementDate: String,
    val type: String
)