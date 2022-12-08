@OptIn(ExperimentalStdlibApi::class)
fun main() {
	fun parseGrid(lines: List<String>) = Array(lines.size) { lineIndex ->
		val line = lines[lineIndex]
		IntArray(line.length) { charIndex -> line[charIndex].digitToInt() }
	}
	
	val partOne = partOne@{ lines: List<String> ->
		val grid = parseGrid(lines)
		val width = grid[0].size
		val height = grid.size
		var count = 2 * (width + height - 2)
		for (y in 1 ..< height - 1) {
			for (x in 1 ..< width - 1) {
				val value = grid[y][x]
				if (
					(0 ..< x).all { grid[y][it] < value }
					|| (x + 1 ..< width).all { grid[y][it] < value }
					|| (0 ..< y).all { grid[it][x] < value }
					|| (y + 1 ..< height).all { grid[it][x] < value }
				) count++
			}
		}
		count
	}
	
	val partTwo = partTwo@{ lines: List<String> ->
		val grid = parseGrid(lines)
		val width = grid[0].size
		val height = grid.size
		var bestScore = 0
		fun Int.coerceBefore(default: Int) = if (this == -1) default else this
		fun Int.coerceAfter(default: Int) = if (this == -1) default else this
		for (y in 1 ..< height - 1) {
			for (x in 1 ..< width - 1) {
				val value = grid[y][x]
				val score = intArrayOf(
					x - (0 ..< x).indexOfLast { grid[y][it] >= value }.coerceBefore(0),
					(x + 1 ..< width).indexOfFirst { grid[y][it] >= value }.coerceAfter(width - x - 2) + 1,
					y - (0 ..< y).indexOfLast { grid[it][x] >= value }.coerceBefore(0),
					(y + 1 ..< height).indexOfFirst { grid[it][x] >= value }.coerceAfter(height - y - 2) + 1
				).product()
				if (score > bestScore) bestScore = score
			}
		}
		bestScore
	}
	
	val testInput = readInputLines("day_8_test")
	checkEquals(partOne(testInput), 21)
	checkEquals(partTwo(testInput), 8)
	
	val input = readInputLines("day_8")
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}
