import java.util.*

fun main(args: Array<String>) = with(Scanner(System.`in`)) {

    val n = nextInt()
    val m = nextInt()
    val map = Array<IntArray>(n){ IntArray(m) }

    for (i in 0 until n) {
        for (j in 0 until m) {
            map[i][j] = nextInt()
        }
    }


}