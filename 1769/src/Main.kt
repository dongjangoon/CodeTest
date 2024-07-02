fun main() {
    var x = readln()
    var count = 0

    while (x.length > 1) {
        x = x.sumOf { it.toString().toInt() }.toString()
        count++
    }

    println(count)
    if (x.toInt() in setOf(3, 6, 9)) {
        println("YES")
    } else {
        println("NO")
    }
}