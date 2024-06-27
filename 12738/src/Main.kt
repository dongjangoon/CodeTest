fun main() {
    val n = readln().toInt()
    val a = readln().split(" ").map { it.toInt() }
    println(lis(a))
}

fun lis(nums: List<Int>): Int {
    if (nums.isEmpty()) return 0

    val tails = mutableListOf<Int>()

    for (num in nums) {
        // 찾고자 하는 값이 배열에 없다면 그 값이 삽입될 위치를 '-(insertion point) - 1'로 반환, 기본 오름차순 기준
        val pos = tails.binarySearch(num)
        if (pos < 0) {
            val insertPos = -(pos + 1)
            if (insertPos >= tails.size) {
                tails.add(num)
            } else {
                tails[insertPos] = num
            }
        }
    }

    return tails.size
}