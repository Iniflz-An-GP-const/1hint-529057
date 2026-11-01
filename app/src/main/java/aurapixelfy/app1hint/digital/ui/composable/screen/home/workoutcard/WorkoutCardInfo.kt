package aurapixelfy.app1hint.digital.ui.composable.screen.home.workoutcard

import androidx.compose.ui.graphics.vector.ImageVector
import aurapixelfy.app1hint.digital.data.model.Difficulty
import aurapixelfy.app1hint.digital.data.model.WorkoutType

data class WorkoutCardInfo(
    val name: String,
    val typeLabel: String,
    val typeIcon: ImageVector,
    val difficultyLabel: String,
    val difficultyLevel: Int,
    val exerciseCount: Int,
    val durationMinutes: Int,
    val type: WorkoutType,
    val difficulty: Difficulty,
    /** Position of the category in `WorkoutType`; the card marks it with the category color. */
    val typeOrdinal: Int = 0,
)
