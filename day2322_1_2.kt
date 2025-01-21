// sudo apt-get update && sudo apt-get install kotlin
// kotlinc day2322_1_2.kt -include-runtime -d day2322_1_2.jar && java -jar day2322_1_2.jar

import java.io.File

fun sandSlab(part: Int): Int {
    var puzzleInput = mutableListOf<Brick>()
	
    // #1 all bricks in snapshot now available
    var id = 0
	  File("day2322_puzzle_input.txt").forEachLine {
      var (minV, maxV) = it.split("~")
      
        puzzleInput.add(Brick(id, minV.split(",")[0].toInt(), maxV.split(",")[0].toInt(), minV.split(",")[1].toInt(), maxV.split(",")[1].toInt(),  minV.split(",")[2].toInt(), maxV.split(",")[2].toInt()))
        id+=1
	}

    // #2 condense snapshot by trying to move a brick down, iterate until no brick is able to move down any more
    // #3 check for each bricks by whom it is supported  -> why no set up a map each cycle and store all id and it's supports
    var move = true
    var supportMap = mutableMapOf<Int, MutableList<Int>>()
    while (move) {
        move = false
        supportMap.clear()
        for (i in 0..puzzleInput.size-1) {
            var collision = false
            var brick1 = puzzleInput[i]
            supportMap.put(brick1.id, mutableListOf())
            if (brick1.zMin > 1) {
                for (j in 0..puzzleInput.size-1) {
                    if (i != j) {
                        val brick2 = puzzleInput[j]
                        if (((brick2.xMin <= brick1.xMax) && (brick2.xMin >= brick1.xMin)) || ((brick1.xMin <= brick2.xMax) && (brick1.xMin >= brick2.xMin))) {
                            if (((brick2.yMin <= brick1.yMax) && (brick2.yMin >= brick1.yMin)) || ((brick1.yMin <= brick2.yMax) && (brick1.yMin >= brick2.yMin))) {
                                if (((brick2.zMin <= brick1.zMax-1) && (brick2.zMin >= brick1.zMin-1)) || ((brick1.zMin-1 <= brick2.zMax) && (brick1.zMin-1 >= brick2.zMin))) {
                                    collision = true
                                    var help = supportMap.getValue(brick1.id)
                                    help.add(brick2.id)
                                }              
                            }               
                        }
                    }
                }
            if (!collision) {
                puzzleInput[i].zMin -=1
                puzzleInput[i].zMax -=1
                move = true
            }
            }
        }
    }

    // #4 ceck for all supporting bricks if there is any brick which is additinally supported by another brick, count all bricks which are not single supporters
    var result = 0
    for (id in 0..supportMap.size-1) {
        var singleSupport = false
        for ((key,value) in supportMap) {
            if (value.contains(id) && value.size == 1) singleSupport = true 
        }
        if (!singleSupport) result +=1
    }
    if (part == 1) return result

    result = 0
    // idea for part2: puzzleInput contains all bricks after situation is stable (this was already calculated)
    // generate list of bricks wich are single support
    // for all single support bricks, 
        //  copy puzzleInput into newList (just not to change stable state of part one)
        //  remove single support brick and start run until stable. At the end, compare to initial state and count id's wich have moved 
    // add up the result

    return result
}

fun main() {

    var t1 = System.currentTimeMillis()

    println("--- Day 22: Sand Slabs ---")

    var solution1 = sandSlab(1)
    println("    $solution1 bricks could be safely chosen")
	   
    var solution2 = sandSlab(2)
    println("    the sum of the number of other bricks that would fall is $solution2")

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
