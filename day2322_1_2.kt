// sudo apt-get update && sudo apt-get install kotlin
// kotlinc day2322_1_2.kt -include-runtime -d day2322_1_2.jar && java -jar day2322_1_2.jar

import java.io.File

fun sandSlap(part = Int): Int {
      var puzzleInput = mutableListOf<Brick>()
	
    var i = 0
	  File("day2322_puzzle_input.txt").forEachLine {
      var (minV, maxV) = it.split("~")
      
        puzzleInput.add(Brick(i, minV.split(",")[0].toInt(), maxV.split(",")[0].toInt(), minV.split(",")[1].toInt(), maxV.split(",")[1].toInt(),  minV.split(",")[2].toInt(), maxV.split(",")[2].toInt() )
        i+=1
	}
     
    println(puzzleInput)
}

  

fun main() {

    var t1 = System.currentTimeMillis()

    println("--- Day 22: Sand Slabs ---")

    var solution1 = sandSlab(1)
    println("    $solution1 bricks could be safely chosen")
	   
    //var solution2 = sandSlab(2)
    //println("   the lagoon can hold $solution2 cubic meters of lava")

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}

data class Brick(
    var id: Int,
    var xMin: Int,
    var xMax: Int,
    var yMin: Int,
    var yMax: Int,
    var zMin: Int,
    var zMax: Int
)
