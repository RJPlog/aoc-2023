// sudo apt-get update && sudo apt-get install kotlin
// kotlinc day2318_1_2.kt -include-runtime -d day2318_1_2.jar && java -jar day2318_1_2.jar

import java.io.File

fun lagoon(part: Int = 2): Long {

    var lagoon = mutableListOf<Pair<Long, Int>>()
    
    // HINT: this does currently not work for part one, following pattern has to be implemented:
    //   rurdu - uluru - dldrd - ldlul
    // otherwise algorithm is blocked.

    if (part == 1) {
        File("day2318_puzzle_input.txt").forEachLine {
            var dist = it.split(" ")[1].toString().toLong()
            var dir = 0
            var dirChar = it.take(1)
            when (dirChar) {
                "R" -> dir = 0
                "D" -> dir = 1
                "L" -> dir = 2
                "U" -> dir = 3
            } 
            lagoon.add(Pair(dist, dir))     
        }
    } else if (part == 2) {
        File("day2318_puzzle_input.txt").forEachLine {
            var dist = it.substringAfter("(#").take(5).toLong(radix = 16)
            var dir = it.substringBefore(")").takeLast(1).toInt()
            lagoon.add(Pair(dist, dir))          
        }
    }
    
    var result = 0L
    
    var i = 0
    var j = 0
    while (lagoon.size > 4  && j < 10000) {    
        var aShapeFound = false
        var bShapeFound = false
        if (lagoon[i].second == 1 &&  lagoon[(i+1)].second == 0 && lagoon[(i+2)].second == 1 && lagoon[(i+3)].second == 2 && lagoon[(i+1)].first < lagoon[(i+3)].first) {
            aShapeFound = true   
        } else if (lagoon[i].second == 3 &&  lagoon[(i+1)].second == 2 && lagoon[(i+2)].second == 3 && lagoon[(i+3)].second == 0 && lagoon[(i+1)].first < lagoon[(i+3)].first) {
            aShapeFound = true
        } else if (lagoon[i].second == 0 &&  lagoon[(i+1)].second == 3 && lagoon[(i+2)].second == 0 && lagoon[(i+3)].second == 1 && lagoon[(i+1)].first < lagoon[(i+3)].first) {
            aShapeFound = true
        } else if (lagoon[i].second == 2 &&  lagoon[(i+1)].second == 1 && lagoon[(i+2)].second == 2 && lagoon[(i+3)].second == 3 && lagoon[(i+1)].first < lagoon[(i+3)].first) {
            aShapeFound = true
        } else if (lagoon[i].second == 0 &&  lagoon[(i+1)].second == 1 && lagoon[(i+2)].second == 2 && lagoon[(i+3)].second == 1 && lagoon[(i)].first > lagoon[(i+2)].first) {
            bShapeFound = true
        } else if (lagoon[i].second == 2 &&  lagoon[(i+1)].second == 3 && lagoon[(i+2)].second == 0 && lagoon[(i+3)].second == 3 && lagoon[(i)].first > lagoon[(i+2)].first) {
            bShapeFound = true
        } else if (lagoon[i].second == 3 &&  lagoon[(i+1)].second == 0 && lagoon[(i+2)].second == 1 && lagoon[(i+3)].second == 0 && lagoon[(i)].first > lagoon[(i+2)].first) {
            bShapeFound = true
        } else if (lagoon[i].second == 1 &&  lagoon[(i+1)].second == 2 && lagoon[(i+2)].second == 3 && lagoon[(i+3)].second == 2 && lagoon[(i)].first > lagoon[(i+2)].first) {
            bShapeFound = true
        }
        if (aShapeFound) {
            result += (lagoon[(i+2)].first + 1) * (lagoon[(i+1)].first)
            lagoon[i] = Pair(lagoon[i].first + lagoon[(i+2)].first, lagoon[i].second)
            lagoon[(i+3)] = Pair(lagoon[(i+3)].first - lagoon[(i+1)].first, lagoon[(i+3)].second)
            lagoon.removeAt((i+2))
            lagoon.removeAt((i+1))
        } else if (bShapeFound) {
            result += (lagoon[(i+1)].first + 1) * (lagoon[(i+2)].first)
            lagoon[i] = Pair(lagoon[i].first - lagoon[(i+2)].first, lagoon[i].second)
            lagoon[(i+3)] = Pair(lagoon[(i+1)].first + lagoon[(i+3)].first, lagoon[(i+3)].second)
            lagoon.removeAt((i+2))
            lagoon.removeAt((i+1))

        } 
         
        var swap = lagoon[0]
        lagoon.removeAt(0)
        lagoon.add(swap)
        j += 1
    }

    result += (lagoon[0].first+1) * (lagoon[1].first+1)
    
    return result
}

fun main() {

    var t1 = System.currentTimeMillis()

    println("--- Day 18: Lavaduct Lagoon---")

    var solution1 = lagoon(1)
    println("   the lagoon can hold $solution1 cubic meters of lava (which is wrong, check comment in line 10-12)")
	   
    var solution2 = lagoon(2)
    println("   the lagoon can hold $solution2 cubic meters of lava")

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
