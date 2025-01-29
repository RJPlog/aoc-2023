// sudo apt-get update && sudo apt-get install kotlin
// kotlinc day2323_1_2.kt -include-runtime -d day2323_1_2.jar && java -jar day2323_1_2.jar

import java.io.File

var w = 0
var pI = ""
fun aocDay2323(part: Int = 1): Int {
    File("day2323_puzzle_input.txt").forEachLine {
        w = it.length
        pI += it
    }
    val h = pI.length / w
    val startIndex = pI.indexOf(".")
    val endIndex = pI.lastIndexOf(".")

    // generate List with connections -> skip eventually, could still check neighbor and direction

    // generate List with junctions

    // set up Dijkstra with longest path
    return -1
}


fun main() {

    var t1 = System.currentTimeMillis()

    println("--- Day 23: A Long Walk ---")

    var solution1 = aocDay2323(1)
    println("   the longest hike is $solution1 steps long")


    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
