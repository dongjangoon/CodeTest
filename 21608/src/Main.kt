import java.util.PriorityQueue

//import java.util.PriorityQueue
//import kotlin.math.pow
//
//val dx = arrayOf(0, 0, 1, -1)
//val dy = arrayOf(1, -1, 0, 0)
//
//data class PointWithCount(val x: Int, val y: Int, val count: Int, val vacancy: Int)
//
//fun main() {
//    val n = readln().toInt()
//    val map = mutableMapOf<Int, List<Int>>()
//    val sequence = mutableListOf<Int>()
//
//    repeat(n.toDouble().pow(2.0).toInt()) {
//        val line = readln().split(" ").map { it.toInt() }
//        val list = mutableListOf(line[1], line[2], line[3], line[4])
//        map[line[0]] = list
//        sequence.add(line[0])
//    }
//
//    val matrix = Array(n) { IntArray(n) { 0 } }
//
//    for (student in sequence) {
//        val candidates = PriorityQueue<PointWithCount>(
//            compareBy<PointWithCount> { -it.count }
//                .thenBy { -it.vacancy }
//                .thenBy { it.x }
//                .thenBy { it.y }
//        )
//
//        for (x in 0 until n) {
//            for (y in 0 until n) {
//                if (matrix[x][y] != 0) continue
//
//                var count = 0 // 인접한 좋아하는 학생 수
//                var vacancy = 0 // 인접한 빈칸 수
//
//                for (k in 0 until 4) {
//
//                    val nx = x + dx[k]
//                    val ny = y + dy[k]
//
//                    if (nx in 0 until n && ny in 0 until n) {
//                        if (map[student]!!.contains(matrix[nx][ny])) count++
//                        else if (matrix[nx][ny] == 0) vacancy++
//                    }
//                }
//
//                candidates.add(PointWithCount(x, y, count, vacancy))
//            }
//        }
//
//        val candidate = candidates.poll()
//        matrix[candidate.x][candidate.y] = student
//    }
//
//    var answer = 0
//    for (x in 0 until n) {
//        for (y in 0 until n) {
//            var count = 0
//            for (k in 0 until 4) {
//                val nx = x + dx[k]
//                val ny = y + dy[k]
//                if (nx in 0 until n && ny in 0 until n) {
//                    if (map[matrix[x][y]]!!.contains(matrix[nx][ny])) count++
//                }
//            }
//
//            when (count) {
//                1 -> answer += 1
//                2 -> answer += 10
//                3 -> answer += 100
//                4 -> answer += 1000
//            }
//        }
//    }
//
//    println(answer)
//}

data class Student(val id: Int, val preferences: List<Int>)
data class Point(val x: Int, val y: Int, val count: Int, val vacancy: Int): Comparable<Point> {
    override fun compareTo(other: Point) = compareValuesBy(
        this, other,
        { -it.count }, { -it.vacancy }, { it.x }, { it.y }
    )
}

val dx = intArrayOf(0, 0, 1, -1)
val dy = intArrayOf(1, -1, 0, 0)

fun main() {
    val n = readln().toInt()
    val students = List(n * n) {
        readln().split(" ").map { it.toInt() }.let { Student(it[0], it.slice(1..4)) }
    }
    val classroom = Array(n) { IntArray(n) }

    students.forEach { placeStudent(it, classroom, n) }

    val satisfaction = calculateSatisfaction(classroom, students.associateBy { it.id }, n)
    println(satisfaction)
}

fun placeStudent(student: Student, classroom: Array<IntArray>, n: Int) {
    val candidates = PriorityQueue<Point>()
    for (x in 0 until n) {
        for (y in 0 until n) {
            if (classroom[x][y] != 0) continue
            var count = 0
            var vacancy = 0
            for (k in 0..3) {
                val nx = x + dx[k]
                val ny = y + dy[k]
                if (nx in 0 until n && ny in 0 until n) {
                    if (classroom[nx][ny] in student.preferences) count++
                    else if (classroom[nx][ny] == 0) vacancy++
                }
            }
            candidates.offer(Point(x, y, count, vacancy))
        }
    }
    val best = candidates.poll()
    classroom[best.x][best.y] = student.id
}

fun calculateSatisfaction(classroom: Array<IntArray>, studentMap: Map<Int, Student>, n: Int): Int {
    var total = 0
    for (x in 0 until n) {
        for (y in 0 until n) {
            var count = 0
            for (k in 0..3) {
                val nx = x + dx[k]
                val ny = y + dy[k]
                if (nx in 0 until n && ny in 0 until n &&
                    classroom[nx][ny] in studentMap[classroom[x][y]]!!.preferences) {
                    count++
                }
            }
            total += when (count) {
                1 -> 1
                2 -> 10
                3 -> 100
                4 -> 1000
                else -> 0
            }
        }
    }
    return total
}