import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.RuntimeException

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val root = Node(br.readLine().toInt())
    var input: String
    while (true) {
        try {
            input = br.readLine()
        } catch (e: RuntimeException) {
            break
        }

        if (input == null) break

        if (input.isEmpty()) break

        root.insert(input.toInt())
    }

    postOrder(root)
}

fun postOrder(node: Node?) {
    if (node == null) return

    postOrder(node.left)
    postOrder(node.right)
    println(node.num)
}

class Node(
    num: Int? = null,
    left: Node? = null,
    right: Node? = null
) {
    var num: Int? = null
    var left: Node? = null
    var right: Node? = null

    init {
        this.num = num
        this.left = left
        this.right = right
    }

    constructor(num: Int) : this(
        num, null, null
    )

    fun insert(n: Int) {
        if (n < num!!) {
            if (left == null) left = Node(n)
            else left!!.insert(n)
        } else {
            if (right == null) right = Node(n)
            else right!!.insert(n)
        }
    }
}