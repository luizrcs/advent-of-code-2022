import java.util.*

fun main() {
	fun findMarkerIndex(datastreamBuffer: String, distinctCount: Int): Int {
		val queue = LinkedList<Char>()
		datastreamBuffer.forEachIndexed { index, char ->
			queue.add(char)
			if (queue.size > distinctCount) {
				queue.pop()
				if (queue.distinct().size == distinctCount) return index + 1
			}
		}
		throw IllegalArgumentException("Invalid datastream buffer")
	}
	
	val partOne = partOne@{ input: String -> findMarkerIndex(input, 4) }
	val partTwo = partTwo@{ input: String -> findMarkerIndex(input, 14) }
	
	val testInput = readInput("day_6_test")
	checkEquals(partOne(testInput), 7)
	checkEquals(partTwo(testInput), 19)
	
	val input = readInput("day_6")
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}
