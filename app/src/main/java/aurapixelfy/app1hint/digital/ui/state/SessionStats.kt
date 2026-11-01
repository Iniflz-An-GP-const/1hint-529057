package aurapixelfy.app1hint.digital.ui.state

import aurapixelfy.app1hint.digital.data.entity.WorkoutSessionEntity
import java.time.DayOfWeek
import java.time.LocalDate

/** Consecutive days with at least one session, counting back from today. */
fun List<WorkoutSessionEntity>.currentStreak(today: LocalDate = LocalDate.now()): Int {
    val sessionDates = map { it.timestamp.toLocalDate() }.toSet()
    var streak = 0
    var day = today
    while (sessionDates.contains(day)) {
        streak++
        day = day.minusDays(1)
    }
    return streak
}

/** Session count for each day of the current week, Monday first. */
fun List<WorkoutSessionEntity>.weeklyCounts(today: LocalDate = LocalDate.now()): List<Int> {
    val weekStart = today.with(DayOfWeek.MONDAY)
    return (0L until 7L).map { offset ->
        val day = weekStart.plusDays(offset)
        count { it.timestamp.toLocalDate() == day }
    }
}
