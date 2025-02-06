import java.io.File
import kotlin.math.abs

fun main() {
	val (firstColumn, secondColumn) = File("1_input.txt").readLines()
		.map {
			val (first, second) = it.split("\\s+".toRegex()).map(String::toInt)
			first to second
		}
		.unzip()

	partOne(firstColumn, secondColumn)
	partTwo(firstColumn, secondColumn)
}

fun partOne(firstColumn: List<Int>, secondColumn: List<Int>) {
	val sum = firstColumn.sorted().zip(secondColumn.sorted())
		.sumOf { (a, b) -> abs(a - b) }

	println("Result part one: $sum")
}

fun partTwo(firstColumn: List<Int>, secondColumn: List<Int>) {
	val multipliers = secondColumn.groupingBy { it }.eachCount()

	val sum = firstColumn.sumOf { it * multipliers.getOrDefault(it, 0) }

	println("Result part two: $sum")
}
