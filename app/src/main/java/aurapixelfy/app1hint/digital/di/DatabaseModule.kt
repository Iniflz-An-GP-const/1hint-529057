package aurapixelfy.app1hint.digital.di

import androidx.room.Room
import aurapixelfy.app1hint.digital.data.database.AXPDLAppDatabase
import org.koin.dsl.module

private const val DB_NAME = "axpdl_sport_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = AXPDLAppDatabase::class.java,
            name = DB_NAME,
        ).build()
    }

    single { get<AXPDLAppDatabase>().workoutSessionDao() }
}
