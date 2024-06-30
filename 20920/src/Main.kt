import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = readln().split(" ").map { it.toInt() }
    val wordCount = mutableMapOf<String, Int>()

    repeat(n) {
        val word = readln()
        if (word.length >= m) {
            wordCount[word] = wordCount.getOrDefault(word, 0) + 1
        }
    }

    wordCount.entries.sortedWith(
        compareByDescending<Map.Entry<String, Int>> { it.value } // value 내림차순
            .thenByDescending { it.key.length } // key 길이가 긴 순서
            .thenBy { it.key } // key 사전순
    ).forEach{ (word, _) ->
        bw.write(word)
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}