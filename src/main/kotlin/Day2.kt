fun main() {
	fun runGame(lines: List<String>, guide: Map<Char, Map<Char, Int>>) = lines.sumOf { line ->
		val (player1, player2) = line.split(" ").map { it.first() }
		guide[player1]!![player2]!!
	}
	
	val partOne = partOne@{ lines: List<String> ->
		val guide = mapOf(
			'A' to mapOf(
				'X' to 4,
				'Y' to 8,
				'Z' to 3,
			),
			'B' to mapOf(
				'X' to 1,
				'Y' to 5,
				'Z' to 9,
			),
			'C' to mapOf(
				'X' to 7,
				'Y' to 2,
				'Z' to 6,
			),
		)
		
		runGame(lines, guide)
	}
	
	val partTwo = partTwo@{ lines: List<String> ->
		val guide = mapOf(
			'A' to mapOf(
				'X' to 3,
				'Y' to 4,
				'Z' to 8,
			),
			'B' to mapOf(
				'X' to 1,
				'Y' to 5,
				'Z' to 9,
			),
			'C' to mapOf(
				'X' to 2,
				'Y' to 6,
				'Z' to 7,
			),
		)
		
		runGame(lines, guide)
	}
	
	val testInput = readInputLines("day_2_test")
	checkEquals(partOne(testInput), 15)
	checkEquals(partTwo(testInput), 12)
	
	val input = readInputLines("day_2")
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}
