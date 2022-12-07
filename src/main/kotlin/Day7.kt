@OptIn(ExperimentalStdlibApi::class)
fun main() {
	fun parseDirs(lines: List<String>): Dir {
		val baseDir = Dir("/")
		var currentDir = baseDir
		
		lines.forEach { line ->
			val split = line.split(' ')
			when (split[0]) {
				"$"   -> {
					when (split[1]) {
						"cd" -> {
							currentDir = when (val targetDir = split[2]) {
								".." -> currentDir.parent ?: baseDir
								"/"  -> baseDir
								else -> currentDir.children.find { it is Dir && it.name == targetDir } as Dir?
									?: Dir(targetDir, currentDir).also { currentDir.children.add(it) }
							}
						}
					}
				}
				"dir" -> {
					val dir = Dir(split[1], currentDir)
					currentDir.children.add(dir)
				}
				else  -> {
					val size = split[0].toInt()
					val name = split[1]
					currentDir.children.add(File(name, size))
				}
			}
		}
		
		return baseDir
	}
	
	val partOne = partOne@{ lines: List<String> ->
		fun sumSizes(dir: Dir): Int {
			var sum = 0
			dir.children
				.filterIsInstance<Dir>()
				.forEach { child ->
					if (child.size <= 100000) sum += child.size
					sum += sumSizes(child)
				}
			return sum
		}
		
		val baseDir = parseDirs(lines)
		sumSizes(baseDir)
	}
	
	val partTwo = partTwo@{ lines: List<String> ->
		val baseDir = parseDirs(lines)
		val neededSize = baseDir.size - 40000000
		var bestSizeFound = baseDir.size
		
		fun findBestSize(dir: Dir) {
			dir.children
				.filterIsInstance<Dir>()
				.forEach { child ->
					if (child.size in neededSize ..< bestSizeFound) bestSizeFound = child.size
					findBestSize(child)
				}
		}
		
		findBestSize(baseDir)
		bestSizeFound
	}
	
	val testInput = readInputLines("day_7_test")
	checkEquals(partOne(testInput), 95437)
	checkEquals(partTwo(testInput), 24933642)
	
	val input = readInputLines("day_7")
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}

open class File(val name: String, open val size: Int = 0) {
	override fun toString() = "$name ($size)"
}

class Dir(name: String, val parent: Dir? = null) : File(name) {
	val children = mutableListOf<File>()
	override val size get() = children.sumOf { it.size }
	override fun toString() = "$name ($size) { ${children.joinToString(", ")}}"
}