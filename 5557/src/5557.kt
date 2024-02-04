import java.util.*

fun main(args: Array<String>) {
    val sc: Scanner = Scanner(System.`in`)

    val n = sc.nextInt()
    val numbers = IntArray(n-1)
    val d = Array(n-1){ LongArray(21) }

    for (i in 0 until n-1) {
        numbers[i] = sc.nextInt()
    }
    val ans = sc.nextInt()

    // TODO: 2021-10-03
    // 1. 계산 과정이든 결과든 0 <=  <= 20

    d[0][numbers[0]] = 1

    for (i in 1 until n-1) {
        for (j in 0..20) {
            if (d[i-1][j] != 0L) {
                if (j + numbers[i] in 0..20) {
                    d[i][j + numbers[i]] = d[i-1][j]
                }
                if (j - numbers[i] in 0..20) {
                    d[i][j - numbers[i]] += d[i-1][j]
                }
            }
        }
    }

    print(d[n-2][ans])
}