package cz.muni.consumption.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// TODO 3.3 DAO
@Dao
interface MeasuredConsumptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun persist(entity: MeasuredConsumptionEntity)

    @Query("SELECT * FROM MeasuredConsumptionEntity ORDER BY measurementDate ASC")
    fun selectAllOrderByDate(): List<MeasuredConsumptionEntity>
}