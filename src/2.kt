import java.io.File

val reports = File("2_input.txt").readLines()

fun main() {
	println("Part one: ${findSafeReports()} safe reports")
	println("Part two: ${findSafeReportsWithTolerance()} safe reports")
}

fun findSafeReports(): Int {
	return reports.count { isReportSafe(it.split(" ").map(String::toInt))}
}

fun findSafeReportsWithTolerance(): Int {
	return reports.count { report ->
        val levels = report.split(" ").map(String::toInt)
        levels.indices.any { i ->
            val levelsWithOneRemoved = levels.toMutableList().apply { removeAt(i) }
            isReportSafe(levelsWithOneRemoved)
        }
    }
}

fun isReportSafe(levels: List<Int>): Boolean {
	val transformedLevels = if (levels[1] > levels[0]) levels else levels.reversed()
	return transformedLevels.zipWithNext().all { (first, second) -> second in first + 1..first + 3}
}

