package cz.muni.consumption.repository.mapper

import cz.muni.consumption.data.ConsumptionType
import cz.muni.consumption.data.MeasuredConsumption
import cz.muni.consumption.database.MeasuredConsumptionEntity
import cz.muni.consumption.util.DateUtil

fun MeasuredConsumptionEntity.toAppData(): MeasuredConsumption =
    MeasuredConsumption(
        id = id,
        consumption = consumption,
        measurementDate = DateUtil.dateFormat.parse(measurementDate),
        type = ConsumptionType.values().firstOrNull { it.name == type } ?: ConsumptionType.ELECTRICITY,
    )

fun MeasuredConsumption.toEntity(): MeasuredConsumptionEntity =
    MeasuredConsumptionEntity(
        id = id,
        consumption = consumption,
        measurementDate = DateUtil.dateFormat.format(measurementDate),
        type = type.name,
    )