val vowel = setOf('a', 'e', 'i', 'o', 'u')

fun main() {
    while (true) {
        val password = readln()
        if (password == "end") break

        if (checkPassword(password)) {
            println("<${password}> is acceptable.")
        } else {
            println("<${password}> is not acceptable.")
        }
    }
}

fun checkPassword(password: String): Boolean {
    if (password.none{ it in vowel }) return false

    var prevChar = ' '
    var vowelCount = 0
    var consonantCount = 0

    for (char in password) {
        if (prevChar == char && char !in setOf('e', 'o')) return false

        if (char in vowel) {
            vowelCount++
            consonantCount = 0
        } else {
            consonantCount++
            vowelCount = 0
        }

        if (vowelCount == 3 || consonantCount == 3) return false

        prevChar = char
    }

    return true
}