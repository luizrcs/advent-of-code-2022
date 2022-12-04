fun main() {
	fun priority(c: Char) = if (c.isLowerCase()) c.code - 'a'.code + 1 else c.code - 'A'.code + 27
	
	val partOne = partOne@{ list: List<String> ->
		list.sumOf { rucksack ->
			val (first, second) = rucksack.chunked(rucksack.length / 2)
			val common = first.toSet().intersect(second.toSet()).first()
			priority(common)
		}
	}
	
	val partTwo = partTwo@{ list: List<String> ->
		list
			.chunked(3)
			.sumOf { (first, second, third) ->
				val common = first.toSet().intersect(second.toSet(), third.toSet()).first()
				priority(common)
			}
	}
	
	val testInput = readInputLines("day_3_test")
	checkEquals(partOne(testInput), 157)
	checkEquals(partTwo(testInput), 70)
	
	val input = readInputLines("day_3")
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}
