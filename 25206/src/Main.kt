fun main() {
    var totalScore = 0.0
    var totalCredit = 0.0
    val gradeMap = mapOf(
        "A+" to 4.5,
        "A0" to 4.0,
        "B+" to 3.5,
        "B0" to 3.0,
        "C+" to 2.5,
        "C0" to 2.0,
        "D+" to 1.5,
        "D0" to 1.0,
        "F" to 0.0,
    )

    repeat(20) {
        val (_, credit, grade) = readln().split(" ")

        if (grade != "P") {
            val creditValue = credit.toDouble()
            totalCredit += creditValue
            totalScore += creditValue * gradeMap.getOrDefault(grade, 0.0)
        }
    }

    val result = totalScore / totalCredit
    println(String.format("%.6f", result))
}