import java.io.File


fun main() {
    val puzzleInput = File("5_input.txt").readLines()
    val rules = readRules(puzzleInput)
    val pagesToProduce = readPagesToProduce(puzzleInput)

    partOne(pagesToProduce, rules)
    partTwo(pagesToProduce, rules)
}

private fun partOne(pagesToProduce: List<List<Int>>, rules: List<Pair<Int, Int>>) {
    val correctlyOrderedPages = pagesToProduce.filter {
        pageInRightOrder(it, rules)
    }

    val result = correctlyOrderedPages.sumOf { page -> findMiddleValue(page) }
    println("Result part one: $result")
}

private fun partTwo(pagesToProduce: List<List<Int>>, rules: List<Pair<Int, Int>>) {
    val result =
        pagesToProduce
            .filterNot { pageInRightOrder(it, rules) }
            .map { fixPage(it, rules) }
            .sumOf { findMiddleValue(it) }

    println("Result part two: $result")
}

private fun fixPage(page: List<Int>, rules: List<Pair<Int, Int>>): List<Int> {
    val fixedPage = page.toMutableList()
    page.forEach { number ->
        val firstElements = rules.filter { it.second == number }.map { it.first }.toSet()
        fixedPage.remove(number)
        fixedPage.add(fixedPage.indexOfLast { it in firstElements } + 1, number)
    }
    return fixedPage
}

private fun findMiddleValue(page: List<Int>): Int {
    val middleValue = page[(page.size - 1) / 2]
    return middleValue
}

private fun pageInRightOrder(page: List<Int>, rules: List<Pair<Int, Int>>): Boolean {
    return page.indices.all { i ->
        rules.none { it.second == page[i] && it.first in page.subList(i + 1, page.size) }
    }
}

private fun readRules(puzzleInput: List<String>): List<Pair<Int, Int>> {
    val result: MutableList<Pair<Int, Int>> = mutableListOf()
    puzzleInput.forEach {
        if (it == "") return result
        val values = it.split("|").map(String::toInt)
        result.add(Pair(values[0], values[1]))
    }
    return result
}

private fun readPagesToProduce(puzzleInput: List<String>): List<List<Int>> {
    val result: MutableList<List<Int>> = mutableListOf()
    val startIndex = puzzleInput.indexOf("") + 1

    for (i in startIndex..<puzzleInput.size) {
        result.add(puzzleInput[i].split(",").map(String::toInt))
    }
    return result
}



