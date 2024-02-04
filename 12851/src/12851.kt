import java.lang.Integer.min
import java.util.*

fun main(args: Array<String>) = with(Scanner(System.`in`)) {

    val n = nextInt()
    val k = nextInt()
    val time = Array<Int>(100001) { 0 }
    var count = 0
    var minTime = 100001
    val queue: Queue<Int> = LinkedList<Int>()

    queue.offer(n)
    time[n] = 1

    if (n >= k) {
        println(n-k)
        println(1)
        return
    }

    while (!queue.isEmpty()) {

        val now = queue.poll()

        // 현재 위치에 오는 데 걸리는 시간이 minTime 보다 크다면
        // next 의 시간도 minTime 보다 클 것이므로 반복문 나감
        if (minTime < time[now]) break;

        for (i in 0..2) {
            var next = 0

            when(i) {
                0 -> next = now+1
                1 -> next = now-1
                2 -> next = now*2
            }

            if (next < 0 || next > 100000) continue

            // 방문하는 곳이 동생의 위치와 같으면 최소 시간
            // 위에서 방문 시간이 큰 경우는 걸렀으므로 바로 minTime
            if (next == k) {
                minTime = time[now]
                count++
            }

            // 첫방문 or 다시 방문했는데 시간이 동일한 경우,
            // 큐에 넣어서 재방문해야함
            if (time[next] == 0 || time[next] == time[now] + 1) {
                queue.offer(next)
                time[next] = time[now] + 1
            }
        }
    }

    println(minTime)
    println(count)

}