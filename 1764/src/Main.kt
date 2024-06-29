fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    val notHeard = mutableSetOf<String>()
    repeat(n) {
        notHeard.add(readln())
    }

    val result = mutableSetOf<String>()
    repeat(m) {
        val name = readln()
        if (name in notHeard) {
            result.add(name)
        }
    }

    println(result.size)
    result.sorted().map { println(it) }
}