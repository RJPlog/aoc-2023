// sudo apt-get update && sudo apt-get install kotlin
// kotlinc day2324_1_2.kt -include-runtime -d day2324_1_2.jar && java -jar -Xss515m day2324_1_2.jar

import java.io.File

fun aocDay2324(part: Int): Int {

    var px = mutableListOf<Int>()
    var py = mutableListOf<Int>()
    var pz = mutableListOf<Int>()

    var vx = mutableListOf<Int>()
    var vy = mutableListOf<Int>()
    var vz = mutableListOf<Int>()

    File("day2324_puzzle_input.txt").forEachLine {
      var pos = it.substringBefore(" @")
      var vel = it.substringAfter("@ ")
      px.add(pos.split(" ,")[0].toInt()
      py.add(pos.split(" ,")[1].toInt()
      pz.add(pos.split(" ,")[2].toInt()
      vx.add(vel.split(" ,")[0].toInt()
      vy.add(vel.split(" ,")[1].toInt()
      vz.add(vel.split(" ,")[2].toInt()
    }

    return -1
}
            

fun main() {

    var t1 = System.currentTimeMillis()

    println("--- Day 24: Never Tell Me The Odds ---")

    //var solution1 = aocDay2324(1)
    //println("   the longest hike is $solution1 steps long")

    var solution2 = aocDay2324(2)
    println("   you get $solution2 if you add up the X, Y, and Z coordinates")

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
