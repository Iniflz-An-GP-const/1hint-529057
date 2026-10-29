package aurapixelfy.app1hint.digital.data.repository

import aurapixelfy.app1hint.digital.data.database.dao.WorkoutSessionDao
import aurapixelfy.app1hint.digital.data.entity.WorkoutSessionEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WorkoutSessionRepository(
    private val workoutSessionDao: WorkoutSessionDao,
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend fun save(session: WorkoutSessionEntity): Long {
        return withContext(coroutineDispatcher) {
            workoutSessionDao.save(session)
        }
    }

    fun observeAll(): Flow<List<WorkoutSessionEntity>> {
        return workoutSessionDao.observeAll()
    }

    suspend fun deleteAll() {
        withContext(coroutineDispatcher) {
            workoutSessionDao.deleteAll()
        }
    }
}
