fun main() {
    val t = readLine()!!.toInt()
    repeat(t) { testCase ->
        val n = readLine()!!.toInt()
        val results = mutableListOf<String>()
        generateExpressions(n, 1, "", 0, 0, results)
        results.sorted().forEach { println(it) }
        if (testCase < t - 1) println() // 마지막 테스트 케이스가 아닐 경우에만 빈 줄 추가
    }
}

fun generateExpressions(n: Int, current: Int, expr: String, value: Long, prevNum: Long, results: MutableList<String>) {
    if (current == n) {
        val finalExpr = expr + current
        if (evaluate(finalExpr) == 0L) results.add(finalExpr)
        return
    }

    // '+' 연산자 추가
    generateExpressions(n, current + 1, "$expr$current+", value + current, current.toLong(), results)

    // '-' 연산자 추가
    generateExpressions(n, current + 1, "$expr$current-", value - current, -current.toLong(), results)

    // 공백(숫자 이어붙이기) 추가
    generateExpressions(n, current + 1, "$expr$current ", value, prevNum * 10 + current, results)
}

fun evaluate(expr: String): Long {
    val tokens = expr.replace(" ", "").replace("-", "+-").split("+")
    return tokens.sumOf { it.toLongOrNull() ?: 0L }
}

