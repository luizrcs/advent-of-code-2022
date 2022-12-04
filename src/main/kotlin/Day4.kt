fun main() {
	fun pairSections(pair: String) = pair.split(',').map { elf ->
		val sections = elf.split('-').map(String::toInt)
		sections[0] .. sections[1]
	}
	
	val partOne = partOne@{ list: List<String> ->
		list.count { pair ->
			val (firstElf, secondElf) = pairSections(pair)
			firstElf.contains(secondElf) || secondElf.contains(firstElf)
		}
	}
	
	val partTwo = partTwo@{ list: List<String> ->
		list.count { pair ->
			val (firstElf, secondElf) = pairSections(pair)
			firstElf.overlaps(secondElf) || secondElf.overlaps(firstElf)
		}
	}
	
	val testInput = readInputLines("day_4_test")
	checkEquals(partOne(testInput), 2)
	checkEquals(partTwo(testInput), 4)
	
	val input = readInputLines("day_4")
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}
