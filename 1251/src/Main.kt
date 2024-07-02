fun main() {
    val word = readln()
    var result = "z".repeat(word.length)

    for (i in 1..word.length - 2) {
        for (j in i + 1 .. word.length - 1) {
            val firstStr = word.substring(0, i);
            val secondStr = word.substring(i, j);
            val thirdStr = word.substring(j, word.length);

            val assembly = firstStr.reversed() + secondStr.reversed() + thirdStr.reversed()

            if (result > assembly) {
                result = assembly
            }
        }
    }
    println(result)
}