import java.util.*
import kotlin.math.min

var a = 0L
var b = 0L
var min = 1000000000L

fun main(args: Array<String>) = with(Scanner(System.`in`)) {

    a = nextInt().toLong()
    b = nextInt().toLong()

    dfs(a, 0)

    // 최솟값이 생기지 않았으면 -1 출력, 아닐시 최솟값+1 출력
    if (min == 1000000000L) {
        println(-1)
    } else {
        println(min+1)
    }
}

fun dfs(a: Long, cnt: Long) {

    // a > b 인 경우 그냥 return
    if (a > b) {
        return
    }

    // a == b 인 경우, cnt 를 현재의 최솟값과 비교해서 작은 것이 min
    if (a == b) {
        min = min(min, cnt)
        return
    }

    dfs((a.toString()+"1").toLong(), cnt+1)
    dfs(a*2, cnt+1)

}