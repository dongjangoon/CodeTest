fun main() {
    val s = readln()
    var t = readln()

    while (t.length >= s.length) {
        if (s == t) {
            println(1)
            return
        }

        t = if (t.last() == 'A') {
            t.dropLast(1)
        } else {
            t.dropLast(1).reversed()
        }
    }

    println(0)
}