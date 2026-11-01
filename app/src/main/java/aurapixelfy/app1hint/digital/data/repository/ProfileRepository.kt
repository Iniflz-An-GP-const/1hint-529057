package aurapixelfy.app1hint.digital.data.repository

import aurapixelfy.app1hint.digital.data.datastore.ProfileDataStoreManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ProfileRepository(
    private val profileDataStoreManager: ProfileDataStoreManager,
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    fun observeWeeklyGoal(): Flow<Int> = profileDataStoreManager.weeklyGoalFlow

    fun observeHeightCm(): Flow<Int?> = profileDataStoreManager.heightCmFlow

    fun observeWeightKg(): Flow<Int?> = profileDataStoreManager.weightKgFlow

    fun observeName(): Flow<String> = profileDataStoreManager.nameFlow

    fun observeAge(): Flow<Int?> = profileDataStoreManager.ageFlow

    fun observeFitnessLevel(): Flow<String?> = profileDataStoreManager.fitnessLevelFlow

    suspend fun setProfile(name: String, age: Int?, fitnessLevel: String) {
        withContext(coroutineDispatcher) {
            profileDataStoreManager.setProfile(name, age, fitnessLevel)
        }
    }

    suspend fun setWeeklyGoal(value: Int?) {
        withContext(coroutineDispatcher) {
            profileDataStoreManager.setWeeklyGoal(value)
        }
    }

    suspend fun setHeightCm(value: Int?) {
        withContext(coroutineDispatcher) {
            profileDataStoreManager.setHeightCm(value)
        }
    }

    suspend fun setWeightKg(value: Int?) {
        withContext(coroutineDispatcher) {
            profileDataStoreManager.setWeightKg(value)
        }
    }
}
