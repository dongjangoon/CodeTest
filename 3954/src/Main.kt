import java.util.Stack

fun main() {
    val t = readln().toInt()

    repeat(t) {
        val (sm, sc, si) = readln().split(" ").map { it.toInt() }
        val program = readln()
        val input = readln()

        val result = runProgram(sm, program, input)
        println(result)
    }
}

fun runProgram(memorySize: Int, program: String, input: String): String {
    val memory = IntArray(memorySize)
    var pointer = 0
    var pc = 0
    var inputPtr = 0
    var totalInstructions = 0

    val loopMap = IntArray(program.length) { -1 }
    val loopStack = Stack<Int>()
    val loopCounts = IntArray(program.length)

    // 전처리: 루프 매칭 정보 생성
    for (i in program.indices) {
        when (program[i]) {
            '[' -> loopStack.push(i)
            ']' -> {
                if (loopStack.isNotEmpty()) {
                    val start = loopStack.pop()
                    loopMap[start] = i
                    loopMap[i] = start
                }
            }
        }
    }

    while (pc < program.length && totalInstructions < 50_000_000) {
        when (program[pc]) {
            '+' -> memory[pointer] = (memory[pointer] + 1) % 256
            '-' -> memory[pointer] = (memory[pointer] - 1 + 256) % 256
            '>' -> pointer = (pointer + 1) % memorySize
            '<' -> pointer = (pointer - 1 + memorySize) % memorySize
            '[' -> {
                if (memory[pointer] == 0) {
                    pc = loopMap[pc]
                } else {
                    loopCounts[pc]++
                    if (loopCounts[pc] > 50_000_000) {
                        return "Loops $pc ${loopMap[pc]}"
                    }
                }
            }
            ']' -> {
                if (memory[pointer] != 0) {
                    pc = loopMap[pc]
                    continue
                }
            }
            ',' -> {
                memory[pointer] = if (inputPtr < input.length) input[inputPtr++].code else 255
            }
        }
        pc++
        totalInstructions++
    }

    return if (totalInstructions >= 50_000_000) {
        // 가장 안쪽의 무한 루프 찾기
        for (i in loopCounts.indices.reversed()) {
            if (loopCounts[i] > 0 && program[i] == '[') {
                return "Loops $i ${loopMap[i]}"
            }
        }
        "Loops 0 ${program.length - 1}"  // 이 경우는 발생하지 않아야 함
    } else {
        "Terminates"
    }
}