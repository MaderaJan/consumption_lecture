package cz.muni.consumption.repository

import android.content.Context
import cz.muni.consumption.data.ConsumptionType
import cz.muni.consumption.data.MeasuredConsumption
import cz.muni.consumption.database.ConsumptionDatabase
import cz.muni.consumption.database.MeasuredConsumptionDao
import cz.muni.consumption.repository.mapper.toEntity
import java.util.*

class ConsumptionRepository(
    context: Context,
    private val dao: MeasuredConsumptionDao = ConsumptionDatabase.create(context).measuredConsumptionDao()
) {

    // TODO 3.5 saveOrUpdate metoda
    fun saveOrUpdate(consumption: Double, date: Date, type: ConsumptionType, id: Long? = null) {
        val measuredConsumption = MeasuredConsumption(
            id = id ?: 0,
            consumption = consumption,
            measurementDate = date,
            type = type,
        )

        // TODO 3.6 mapping extensions
        dao.persist(measuredConsumption.toEntity())
    }

    fun getAllMeasuredConsumption(): List<MeasuredConsumption> =
    // TODO 5. (S) select a map data z DB
        // HINT: MeasuredConsumptionEntity.toAppData()
        emptyList()
}