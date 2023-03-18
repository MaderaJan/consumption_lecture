package cz.muni.consumption.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MeasuredConsumptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun persist(entity: MeasuredConsumptionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun persist(entity: List<MeasuredConsumptionEntity>)

    @Query("SELECT * FROM MeasuredConsumptionEntity ORDER BY measurementDate ASC")
    fun selectAllOrderByDate(): List<MeasuredConsumptionEntity>
}