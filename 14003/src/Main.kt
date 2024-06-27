fun main() {
    val n = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }

    if (n == 1) {
        println(1)
        println(arr[0])
        return
    }

    val lis: MutableList<Int> = mutableListOf()
    val indices = IntArray(n)

    lis.add(arr[0])
    indices[0] = 0

    for (i in 1 ..< n) {
        if (arr[i] > lis.last()) {
            lis.add(arr[i])
            indices[i] = lis.size - 1
        } else {
            val pos = lowerBound(lis, arr[i])
            lis[pos] = arr[i]
            indices[i] = pos
        }
    }

    println(lis.size)

    val result = IntArray(lis.size)
    var k = lis.size - 1
    for (i in n - 1 downTo 0) {
        if (indices[i] == k) {
            result[k] = arr[i]
            k--
        }
    }

    println(result.joinToString(" "))
}

fun lowerBound(list: MutableList<Int>, target: Int): Int {
    var left = 0
    var right = list.size
    
    while (left < right) {
        val mid = (left + right) / 2
        if (list[mid] < target) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    return right
}