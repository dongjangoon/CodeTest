fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val set = mutableSetOf<String>()

    repeat(n) {
        val str = readln()
        set.add(str)
    }

    var result = 0
    repeat(m) {
        val str = readln()
        if (set.contains(str)) {
            result++
        }
    }

    println(result)
}