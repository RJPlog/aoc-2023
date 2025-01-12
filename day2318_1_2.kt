// sudo apt-get update && sudo apt-get install kotlin
// kotlinc day2318_1_2.kt -include-runtime -d day2318_1_2.jar && java -jar day2318_1_2.jar


import java.io.File

fun lagoon(part: Int = 0): Long {

    //File("day2318_puzzle_input.txt").forEachLine {

    //}
    
    var puzzleInput = listOf("R 6 (#70c710)", "D 5 (#0dc571)", "L 2 (#5713f0)", "D 2 (#d2c081)", "R 2 (#59c680)", "D 2 (#411b91)", "L 5 (#8ceee2)", "U 2 (#caa173)", "L 1 (#1b58a2)", "U 2 (#caa171)", "R 2 (#7807d2)", "U 3 (#a77fa3)", "L 2 (#015232)", "U 2 (#7a21e3)")


	var lagoon = mutableListOf<Pair<Long, Int>>()
    
    puzzleInput.forEach {
        var dist = it.substring(6, 11).toLong(radix = 16)
        var dir = it[11].toString().toInt()
        lagoon.add(Pair(dist, dir))
    }
    

    println(lagoon)
    
    var result = 0L
    
    var i = 0
    var j = 0
    while (lagoon.size > 4 && j < 30) {
        
        var f = lagoon.size
        var aShapeFound = false
        if (lagoon[i%f].second == 1 &&  lagoon[(i+1)%f].second == 0 && lagoon[(i+2)%f].second == 1 && lagoon[(i+3)%f].second == 2 && lagoon[(i+1)%f].first < lagoon[(i+3)%f].first) {
            aShapeFound = true   
        } else if (lagoon[i%f].second == 3 &&  lagoon[(i+1)%f].second == 2 && lagoon[(i+2)%f].second == 3 && lagoon[(i+3)%f].second == 0 && lagoon[(i+1)%f].first < lagoon[(i+3)%f].first) {
            aShapeFound = true
        } else if (lagoon[i%f].second == 0 &&  lagoon[(i+1)%f].second == 3 && lagoon[(i+2)%f].second == 0 && lagoon[(i+3)%f].second == 1 && lagoon[(i+1)%f].first < lagoon[(i+3)%f].first) {
            aShapeFound = true
        } else if (lagoon[i%f].second == 2 &&  lagoon[(i+1)%f].second == 1 && lagoon[(i+2)%f].second == 2 && lagoon[(i+3)%f].second == 3 && lagoon[(i+1)%f].first < lagoon[(i+3)%f].first) {
            aShapeFound = true
        }
        if (aShapeFound) {
            result += (lagoon[(i+2)%f].first + 1) * (lagoon[(i+1)%f].first)
            lagoon[i%f] = Pair(lagoon[i%f].first + lagoon[(i+2)%f].first, lagoon[i].second)
            lagoon[(i+3)%f] = Pair(lagoon[(i+3)%f].first - lagoon[(i+1)%f].first, lagoon[(i+3)%f].second)
            lagoon.removeAt((i+2)%f)
            lagoon.removeAt((i+1)%f)
            println("treffer $i${i+1}${i+2}${i+3}: result = $result")
        }
        i += 1
        j += 1
        if (i > lagoon.size-1) i = 0
    }
    lagoon.forEach{
        println("${it.first};${it.second}")
    }
    
    return result
}

   


fun main() {

    var t1 = System.currentTimeMillis()

    println("--- Day 18: Lavaduct Lagoon---")
    
    //var solution1 = lagoon(1)
    //println("   the lagoon can hold $solution1 cubic meters of lava")
	   
    var solution2 = lagoon(2)
    //println("   the lagoon can hold $solution2 cubic meters of lava")

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
