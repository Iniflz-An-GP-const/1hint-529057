package aurapixelfy.app1hint.digital.data.model

private const val MS_PER_REPETITION = 3_000L
private const val MS_PER_MINUTE = 60_000L

/** Work plus rest over every set; a rep-based set counts a few seconds per repetition. */
fun ExerciseModel.estimatedMs(): Long {
    val setCount = sets ?: 1
    val workMs = duration ?: ((repetitions ?: 0) * MS_PER_REPETITION)
    return setCount * (workMs + (restDuration ?: 0L))
}

/** Estimated length of the whole workout in whole minutes, never less than one. */
fun WorkoutModel.estimatedMinutes(): Int {
    val totalMs = exercises.sumOf { exercise -> exercise.estimatedMs() }
    return ((totalMs + MS_PER_MINUTE - 1) / MS_PER_MINUTE).toInt().coerceAtLeast(1)
}
