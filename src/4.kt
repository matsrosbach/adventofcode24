import java.io.File

val puzzleInput = File("4_input.txt").readLines()
val array2D = padArrayWithZ(convertTo2DArray(puzzleInput))

fun main() {
	partOne()
	partTwo()
}

private fun partOne() {
	val directions = listOf(
		listOf(Pair(0, 1), Pair(0, 2), Pair(0, 3)), // Right
		listOf(Pair(0, -1), Pair(0, -2), Pair(0, -3)), // Left
		listOf(Pair(-1, 0), Pair(-2, 0), Pair(-3, 0)), // Up
		listOf(Pair(1, 0), Pair(2, 0), Pair(3, 0)), // Down
		listOf(Pair(1, 1), Pair(2, 2), Pair(3, 3)), // Down right
		listOf(Pair(1, -1), Pair(2, -2), Pair(3, -3)), // Down left
		listOf(Pair(-1, -1), Pair(-2, -2), Pair(-3, -3)), // Up left
		listOf(Pair(-1, 1), Pair(-2, 2), Pair(-3, 3)) // Up right
	)
	val letters = listOf('M', 'A', 'S')
	var result = 0

	for (row in array2D.indices) {
		for (col in array2D[row].indices) {
			if (array2D[row][col] == 'X') {
				for (direction in directions) {
					if (direction.indices.all { i -> array2D[row + direction[i].first][col + direction[i].second] == letters[i] }) {
						result++
					}
				}
			}
		}
	}

	println("Part one: $result")
}


private fun partTwo() {
	var result = 0
	val letterConfigurations = listOf(
		listOf(Triple(-1, -1, 'M'), Triple(-1, 1, 'M'), Triple(1, -1, 'S'), Triple(1, 1, 'S')),
		listOf(Triple(-1, -1, 'S'), Triple(-1, 1, 'S'), Triple(1, -1, 'M'), Triple(1, 1, 'M')),
		listOf(Triple(-1, -1, 'M'), Triple(-1, 1, 'S'), Triple(1, -1, 'M'), Triple(1, 1, 'S')),
		listOf(Triple(-1, -1, 'S'), Triple(-1, 1, 'M'), Triple(1, -1, 'S'), Triple(1, 1, 'M'))
	)
	for (row in array2D.indices) {
		for (col in array2D[row].indices) {
			if (array2D[row][col] == 'A') {
				for (configuration in letterConfigurations) {
					if (configuration.indices.all { i ->
						array2D[row + configuration[i].first][col + configuration[i].second] == configuration[i].third 
					}) {
						result++
					}
				}
			}
		}
	}
	println("Part two: $result")
}

fun convertTo2DArray(strings: List<String>): Array<CharArray> {
    val numRows = strings.size
    val numCols = strings[0].length
    val array2D = Array(numRows) { CharArray(numCols) }

    for (i in strings.indices) {
        for (j in strings[i].indices) {
            array2D[i][j] = strings[i][j]
        }
    }

    return array2D
}

fun padArrayWithZ(array: Array<CharArray>): Array<CharArray> {
    val originalRows = array.size
    val originalCols = array[0].size
    val paddedRows = originalRows + 6
    val paddedCols = originalCols + 6

    val paddedArray = Array(paddedRows) { CharArray(paddedCols) { 'Z' } }

    for (i in array.indices) {
        for (j in array[i].indices) {
            paddedArray[i + 3][j + 3] = array[i][j]
        }
    }

    return paddedArray
}


