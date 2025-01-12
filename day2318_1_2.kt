// sudo apt-get update && sudo apt-get install kotlin
// kotlinc day2318_1_2.kt -include-runtime -d day2318_1_2.jar && java -jar day2318_1_2.jar


import java.io.File

fun lagoon(part: Int = 2): Long {

    var lagoon = mutableListOf<Pair<Long, Int>>()

    if (part == 2) {
        File("day2318_puzzle_input.txt").forEachLine {
            var dist = it.substringAfter("(#").take(5).toLong(radix = 16)
            var dir = it.substringBefore(")").takeLast(1).toInt()
            lagoon.add(Pair(dist, dir))          
        }
    }
    

    println(lagoon)
    
    var result = 0L
    
    var i = 0
    var j = 0
    while (lagoon.size > 4 && j < 30) {
        
        var f = lagoon.size
        var aShapeFound = false
        var bShapeFound = false
        if (lagoon[i%f].second == 1 &&  lagoon[(i+1)%f].second == 0 && lagoon[(i+2)%f].second == 1 && lagoon[(i+3)%f].second == 2 && lagoon[(i+1)%f].first < lagoon[(i+3)%f].first) {
            aShapeFound = true   
        } else if (lagoon[i%f].second == 3 &&  lagoon[(i+1)%f].second == 2 && lagoon[(i+2)%f].second == 3 && lagoon[(i+3)%f].second == 0 && lagoon[(i+1)%f].first < lagoon[(i+3)%f].first) {
            aShapeFound = true
        } else if (lagoon[i%f].second == 0 &&  lagoon[(i+1)%f].second == 3 && lagoon[(i+2)%f].second == 0 && lagoon[(i+3)%f].second == 1 && lagoon[(i+1)%f].first < lagoon[(i+3)%f].first) {
            aShapeFound = true
        } else if (lagoon[i%f].second == 2 &&  lagoon[(i+1)%f].second == 1 && lagoon[(i+2)%f].second == 2 && lagoon[(i+3)%f].second == 3 && lagoon[(i+1)%f].first < lagoon[(i+3)%f].first) {
            aShapeFound = true
        } else if (lagoon[i%f].second == 0 &&  lagoon[(i+1)%f].second == 1 && lagoon[(i+2)%f].second == 2 && lagoon[(i+3)%f].second == 1 && lagoon[(i+1)%f].first > lagoon[(i+2)%f].first) {
            bShapeFound = true
        } else if (lagoon[i%f].second == 2 &&  lagoon[(i+1)%f].second == 3 && lagoon[(i+2)%f].second == 0 && lagoon[(i+3)%f].second == 3 && lagoon[(i+1)%f].first > lagoon[(i+2)%f].first) {
            bShapeFound = true
        } else if (lagoon[i%f].second == 3 &&  lagoon[(i+1)%f].second == 0 && lagoon[(i+2)%f].second == 1 && lagoon[(i+3)%f].second == 0 && lagoon[(i+1)%f].first > lagoon[(i+2)%f].first) {
            bShapeFound = true
        } else if (lagoon[i%f].second == 1 &&  lagoon[(i+1)%f].second == 2 && lagoon[(i+2)%f].second == 3 && lagoon[(i+3)%f].second == 2 && lagoon[(i+1)%f].first > lagoon[(i+2)%f].first) {
            bShapeFound = true
        }
        if (aShapeFound) {
            result += (lagoon[(i+2)%f].first + 1) * (lagoon[(i+1)%f].first)
            lagoon[i%f] = Pair(lagoon[i%f].first + lagoon[(i+2)%f].first, lagoon[i].second)
            lagoon[(i+3)%f] = Pair(lagoon[(i+3)%f].first - lagoon[(i+1)%f].first, lagoon[(i+3)%f].second)
            lagoon.removeAt((i+2)%f)
            lagoon.removeAt((i+1)%f)
            println("$i: a - treffer $i${i+1}${i+2}${i+3}: result = $result")
        } else if (bShapeFound) {
            result += (lagoon[(i+1)%f].first + 1) * (lagoon[(i+2)%f].first)
            lagoon[i%f] = Pair(lagoon[i%f].first - lagoon[(i+2)%f].first, lagoon[i].second)
            lagoon[(i+3)%f] = Pair(lagoon[(i+1)%f].first + lagoon[(i+3)%f].first, lagoon[(i+3)%f].second)
            lagoon.removeAt((i+2)%f)
            lagoon.removeAt((i+1)%f)
            println("$i: b - treffer $i${i+1}${i+2}${i+3}: result = $result")
        }
        i += 1
        j += 1
        if (i > lagoon.size-1) i = 0
    }
    lagoon.forEach{
        println("${it.first};${it.second}")
    }

    result += (lagoon[0].first+1) * (lagoon[1].first+1)
    
    return result
}

fun main() {

    var t1 = System.currentTimeMillis()

    println("--- Day 18: Lavaduct Lagoon---")
    
    //var solution1 = lagoon(1)
    //println("   the lagoon can hold $solution1 cubic meters of lava")
	   
    var solution2 = lagoon(2)
    println("   the lagoon can hold $solution2 cubic meters of lava")

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
