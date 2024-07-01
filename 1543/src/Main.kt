fun main() {
    val document = readln()
    val word = readln()

    var count = 0
    var index = 0

    while (index <= document.length - word.length) {
        if (document.substring(index, index + word.length) == word) {
            count++
            index += word.length
        } else {
            index++
        }
    }

    println(count)
}