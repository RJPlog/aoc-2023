// sudo apt-get update && sudo apt-get install kotlin
// kotlinc day2323_1_2.kt -include-runtime -d day2323_1_2.jar && java -jar day2323_1_2.jar

import java.io.File

fun move2(sI: Pair<Int,Int>, dir: Char, eI: Pair<Int,Int>, j: MutableMap<Pair<Int,Int>,Char>, count: Int): Int {
    //println(".".repeat(count) + count + sI+ "," + eI)
    var result = mutableListOf(0)
    if (sI == eI) {
        result.add(0)
        println(count)
        return 0
    } else {
        // up
        if (dir != 'd' && j.containsKey(Pair(sI.first, sI.second-1))) {
            var newJU = mutableMapOf<Pair<Int,Int>,Char>()
            newJU.putAll(j)
            newJU.remove(Pair(sI.first, sI.second),'.')
            result.add(1 + move2(Pair(sI.first, sI.second-1), 'u', eI, newJU, count +1))
        }
        // right
        if (dir != 'l' && j.containsKey(Pair(sI.first+1, sI.second))) {
            var newJR = mutableMapOf<Pair<Int,Int>,Char>()
            newJR.putAll(j)
            newJR.remove(Pair(sI.first, sI.second),'.')
            result.add(1 + move2(Pair(sI.first+1, sI.second), 'r', eI, newJR, count +1))
        }
        // down
        if (dir != 'u' && j.containsKey(Pair(sI.first, sI.second+1))) {
            var newJD = mutableMapOf<Pair<Int,Int>,Char>()
            newJD.putAll(j)
            newJD.remove(Pair(sI.first, sI.second),'.')
            result.add(1 + move2(Pair(sI.first, sI.second+1), 'd', eI, newJD, count +1))
        }
        // left
        if (dir != 'r' && j.containsKey(Pair(sI.first-1, sI.second))) {
            var newJL = mutableMapOf<Pair<Int,Int>,Char>()
            newJL.putAll(j)
            newJL.remove(Pair(sI.first, sI.second),'.')
            result.add(1 + move2(Pair(sI.first-1, sI.second), 'l', eI, newJL, count +1))
        }
    }
    result.sortDescending()
    println(result)
    return result[0]
}

fun move(sI: Pair<Int,Int>, dir: Char, eI: Pair<Int,Int>, j: MutableMap<Pair<Int,Int>,Char>, count: Int): Int {
    var result = mutableListOf(0)
    if (sI == eI) {
        result.add(0)
        println(count)
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
    return result[0]
}



fun aocDay2323(part: Int = 1): Int {
var junctions = mutableMapOf<Pair<Int, Int>, Char>()
var y = 0
var w = 0

var pI = ""
    File("day2323_puzzle_input.txt").forEachLine {
            var x = 0
            pI += it
            w = it.length
            it.forEach{
                if (it!='#') {
                    if (part == 1) {
                        junctions.put(Pair(x,y), it)
                    } else {
                        junctions.put(Pair(x,y), '.')
                    }
                    
                }
                x+=1
            }
            y+=1
    }

    val startIndex = Pair(pI.indexOf(".") % w, pI.indexOf(".") / w)
    val endIndex = Pair(pI.lastIndexOf(".") % w, pI.lastIndexOf(".") / w)
    println(endIndex)

    var result = 0
    if (part == 1) {
        result = move(startIndex, 'd', endIndex, junctions, 1)
    } else {
        result = move2(startIndex, 'd', endIndex, junctions, 2)
    } 
    return result
}


fun main() {

    var t1 = System.currentTimeMillis()

    println("--- Day 23: A Long Walk ---")

    var solution1 = aocDay2323(1)
    println("   the longest hike is $solution1 steps long")

    var solution2 = aocDay2323(2)
    println("   the longest hike is $solution2 steps long")

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
