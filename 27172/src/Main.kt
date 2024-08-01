fun main() {
    val n = readln().toInt()
    val cards = readln().split(" ").map { it.toInt() }
    val maxNumber = cards.maxOrNull()!!

    val scores = IntArray(n)
    val cardIndexes = IntArray(maxNumber+1)

//    for (i in 0 until n) {
//        cardIndexes[cards[i]] = i + 1
//    }
//
//    for (mod in cards) {
//        for (i in mod * 2 .. maxNumber step mod) {
//            if (cardIndexes[i] != 0) {
//                scores[cardIndexes[mod]]++
//                scores[cardIndexes[i]]--
//            }
//        }
//    }

    cards.forEachIndexed { index, card ->
        cardIndexes[card] = index + 1
    }

    cards.forEachIndexed { index, mod ->
        (mod * 2..maxNumber step mod)
            .filter { cardIndexes[it] != 0 }
            .forEach {
                scores[index]++
                scores[cardIndexes[it] - 1]--
            }
    }

    println(scores.joinToString(" "))
}