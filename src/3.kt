import java.io.File

val memory = File("3_input.txt").readLines()

fun main() {
	partOne()
	partTwo()
}

private fun partOne() {
	println("Sum part 1: ${performMul(memory.joinToString(""))}")
}

private fun partTwo() {
	val disabledInstructionsRegex = Regex("don't\\(\\)(.*?)(do\\(\\)|$)")
	val memoryWithoutDisabledInstructions = disabledInstructionsRegex.replace(memory.joinToString(""), "")

	println("Sum part 2: ${performMul(memoryWithoutDisabledInstructions)}")
}

fun performMul(input: String): Int {
	return Regex("mul\\(\\d+\\,\\d+\\)").findAll(input).map {
		val argumentsToSum = Regex("\\d+").findAll(it.value)
		argumentsToSum.map { it.value.toInt() }.reduce { acc, ele -> acc * ele }
	}.sum()
}
