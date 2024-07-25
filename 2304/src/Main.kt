fun main() {
    val n = readln().toInt()
    val height = IntArray(1001)
    var start = Int.MAX_VALUE
    var end = 0

    repeat(n) {
        val (l, h) = readln().split(" ").map { it.toInt() }
        height[l] = h
        start = minOf(start, l)
        end = maxOf(end, l)
    }

    val stack = mutableListOf<Int>()

    // 왼쪽
    var temp = height[start]
    for (i in start + 1 .. end) {
        if (height[i] < temp) {
            stack.add(i)
        } else {
            while (stack.isNotEmpty()) {
                val x = stack.removeLast()
                height[x] = temp
            }
            temp = height[i]
        }
    }
    stack.clear()

    // 오른쪽
    temp = height[end]
    for (i in end - 1 downTo start) {
        if (height[i] < temp) {
            stack.add(i)
        } else {
            while (stack.isNotEmpty()) {
                val x = stack.removeLast()
                height[x] = temp
            }
            temp = height[i]
        }
    }

    var area = 0
    for (i in start .. end) {
        area += height[i]
    }

    println(area)
}