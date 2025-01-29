// sudo apt-get update && sudo apt-get install kotlin
// kotlinc day2323_1_2.kt -include-runtime -d day2323_1_2.jar && java -jar day2323_1_2.jar

import java.io.File

fun move(sI: Pair<Int,Int>, dir: Char, eI: Pair<Int,Int>, j: MutableMap<Pair<Int,Int>,Char>, count: Int): Int {

    //if (count == 15) return 0
    //print("move $sI, $dir:") 
    //println()
    var result = mutableListOf(0)
    if (sI == eI) {
        result.add(1)
    } else {
        // up
            if (dir != 'd' && j.containsKey(Pair(sI.first, sI.second-1))) {
                if (j.getValue(Pair(sI.first, sI.second-1)) != 'v') {
                    result.add(1 + move(Pair(sI.first, sI.second-1), 'u', eI, j, count +1 ))
                }
            }
        // right
        if (dir != 'l' && j.containsKey(Pair(sI.first+1, sI.second))) {
            if (j.getValue(Pair(sI.first+1, sI.second)) != '<') {
                result.add(1 + move(Pair(sI.first+1, sI.second), 'r', eI, j, count +1))
            }
        }
        // down
        if (dir != 'u' && j.containsKey(Pair(sI.first, sI.second+1))) {
            if (j.getValue(Pair(sI.first, sI.second+1)) != '^') {
                result.add(1 + move(Pair(sI.first, sI.second+1), 'd', eI, j, count +1))
            }
        }
        // left
        if (dir != 'r' && j.containsKey(Pair(sI.first-1, sI.second))) {
            if (j.getValue(Pair(sI.first-1, sI.second)) != '>') {
                result.add(1 + move(Pair(sI.first-1, sI.second), 'l', eI, j, count +1))
            }
        }
    }
    result.sortDescending()
    //println(result)
    return result[0]
}


var junctions = mutableMapOf<Pair<Int, Int>, Char>()
var y = 0
var w = 0
var pI = ""
fun aocDay2323(part: Int = 1): Int {
    File("day2323_puzzle_input.txt").forEachLine {
            var x = 0
            pI += it
            w = it.length
            it.forEach{
                if (it!='#') {
                    junctions.put(Pair(x,y), it)
                }
                x+=1
            }
            y+=1
    }

    //println(junctions)
    val startIndex = Pair(pI.indexOf(".") % w, pI.indexOf(".") / w)
    val endIndex = Pair(pI.lastIndexOf(".") % w, pI.lastIndexOf(".") / w)

    val result = move(startIndex, 'd', endIndex, junctions, 1) 
    return result
}


fun main() {

    var t1 = System.currentTimeMillis()

    println("--- Day 23: A Long Walk ---")

    var solution1 = aocDay2323(1)
    println("   the longest hike is $solution1 steps long")  // 2219 is to high

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
