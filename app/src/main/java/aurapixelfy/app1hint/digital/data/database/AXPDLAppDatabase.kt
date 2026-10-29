package aurapixelfy.app1hint.digital.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import aurapixelfy.app1hint.digital.data.database.converter.Converters
import aurapixelfy.app1hint.digital.data.database.dao.WorkoutSessionDao
import aurapixelfy.app1hint.digital.data.entity.WorkoutSessionEntity

@Database(
    entities = [WorkoutSessionEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class AXPDLAppDatabase : RoomDatabase() {

    abstract fun workoutSessionDao(): WorkoutSessionDao
}
