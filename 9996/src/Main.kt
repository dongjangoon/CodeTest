fun main() {
    val n = readln().toInt()
    val pattern = readln()

    val regex = pattern.replace("*", ".*").toRegex()

    repeat(n) {
        val file = readln()
        if (regex.matches(file)) {
            println("DA")
        } else {
            println("NE")
        }
    }
}