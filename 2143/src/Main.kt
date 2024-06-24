fun main() {
    val t = readln().toInt()
    val n = readln().toInt()
    val a = readln().split(" ").map { it.toInt() }
    val m = readln().toInt()
    val b = readln().split(" ").map { it.toInt() }

    // a의 모든 부분집합의 합 구하기
    val mapA = mutableMapOf<Long, Long>()
    for (i in 0..< n) {
        var sum = 0L;
        for (j in i ..< n) {
            sum += a[j]
            mapA[sum] = mapA.getOrDefault(sum, 0) + 1
        }
    }

    // b의 부분합과 비교해서 부족한 부분이 일치하면 카운트
    var count = 0L;
    for (i in 0 ..< m) {
        var sum = 0L;
        for (j in i ..< m) {
            sum += b[j]
            val complement = t - sum
            if (mapA.containsKey(complement)) {
                count += mapA[complement]!!
            }
        }
    }

    println(count)
}
