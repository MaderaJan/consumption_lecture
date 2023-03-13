package cz.muni.consumption.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// TODO 3.2 DB
@Database(
    entities = [MeasuredConsumptionEntity::class],
    version = 1
)
abstract class ConsumptionDatabase : RoomDatabase() {

    companion object {
        private const val NAME = "consumption.db"

        fun create(context: Context): ConsumptionDatabase =
            Room.databaseBuilder(context, ConsumptionDatabase::class.java, NAME)
                .allowMainThreadQueries()
                .build()
    }

    abstract fun measuredConsumptionDao(): MeasuredConsumptionDao
}