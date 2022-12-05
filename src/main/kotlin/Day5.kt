import java.util.*

fun main() {
	fun parseCrates(line: String, deques: MutableList<Deque<Char>>) {
		val crates = line.chunked(4).map { chunk -> chunk[1] }
		crates.forEachIndexed { index, char ->
			if (index >= deques.size) deques.add(ArrayDeque())
			if (char != ' ' && !char.isDigit()) deques[index].addFirst(char)
		}
	}
	
	fun parseMove(line: String) = Regex("move (\\d+) from (\\d+) to (\\d+)").find(line)!!.groupValues.drop(1).map { it.toInt() }
	
	fun dequeTopsString(deques: List<Deque<Char>>) = String(deques.map { it.last() }.toCharArray())
	
	val partOne = partOne@{ list: List<String> ->
		val deques = mutableListOf<Deque<Char>>()
		
		list.forEach { line ->
			if (!line.startsWith("move")) parseCrates(line, deques)
			else {
				val (amount, from, to) = parseMove(line)
				val fromDeque = deques[from - 1]
				val toDeque = deques[to - 1]
				repeat(amount) { toDeque.addLast(fromDeque.pollLast()) }
			}
		}
		
		dequeTopsString(deques)
	}
	
	val partTwo = partTwo@{ list: List<String> ->
		val deques = mutableListOf<Deque<Char>>()
		
		list.forEach { line ->
			if (!line.startsWith("move")) parseCrates(line, deques)
			else {
				val (amount, from, to) = parseMove(line)
				val fromDeque = deques[from - 1]
				val toDeque = deques[to - 1]
				List(amount) { fromDeque.pollLast() }.reversed().forEach { toDeque.addLast(it) }
			}
		}
		
		dequeTopsString(deques)
	}
	
	val testInput = readInputLines("day_5_test")
	checkEquals(partOne(testInput), "CMZ")
	checkEquals(partTwo(testInput), "MCD")
	
	val input = readInputLines("day_5")
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}
