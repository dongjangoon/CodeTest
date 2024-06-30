fun main() {
    val t = readln().toInt()
    val pattern = "^(100+1+|01)+$".toRegex()

    repeat(t) {
        val str = readln()
        if (pattern.matches(str)) {
            println("YES")
        } else {
            println("NO")
        }
    }
}