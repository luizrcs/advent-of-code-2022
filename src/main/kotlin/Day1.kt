fun main() {
	val partOne = partOne@{ lines: List<String> ->
		lines
			.splitOn { it.isEmpty() }
			.maxOf { calories -> calories.sumOf(String::toInt) }
	}
	
	val partTwo = partTwo@{ lines: List<String> ->
		lines
			.splitOn { it.isEmpty() }
			.map { calories -> calories.sumOf(String::toInt) }
			.sortedDescending()
			.take(3)
			.sum()
	}
	
	val testInput = readInputLines("day_1_test")
	checkEquals(partOne(testInput), 24000)
	checkEquals(partTwo(testInput), 45000)
	
	val input = readInputLines("day_1")
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}
