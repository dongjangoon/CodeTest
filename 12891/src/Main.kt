fun main() {
    val (s, p) = readln().split(" ").map { it.toInt() }
    val dnaStr = readln()
    val (a, c, g, t) = readln().split(" ").map { it.toInt() }

    val dnaMap = mutableMapOf(
        'A' to 0,
        'C' to 0,
        'G' to 0,
        'T' to 0
    )
    val requiredMap = mapOf('A' to a, 'C' to c, 'G' to g, 'T' to t)
    var result = 0

    for (i in 0..<p) {
        dnaMap[dnaStr[i]] = dnaMap[dnaStr[i]]!! + 1
    }

    if (checkPassword(dnaMap, requiredMap)) result++

    for (i in p..<s) {
        dnaMap[dnaStr[i - p]] = dnaMap[dnaStr[i - p]]!! - 1
        dnaMap[dnaStr[i]] = dnaMap[dnaStr[i]]!! + 1

        if (checkPassword(dnaMap, requiredMap)) result++
    }

    println(result)
}

fun checkPassword(dnaMap: Map<Char, Int>, requiredMap: Map<Char, Int>): Boolean {
    return dnaMap['A']!! >= requiredMap['A']!! &&
            dnaMap['C']!! >= requiredMap['C']!! &&
            dnaMap['G']!! >= requiredMap['G']!! &&
            dnaMap['T']!! >= requiredMap['T']!!
}