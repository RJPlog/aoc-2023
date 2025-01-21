// sudo apt-get update && sudo apt-get install kotlin
// kotlinc day2322_1_2.kt -include-runtime -d day2322_1_2.jar && java -jar day2322_1_2.jar

import java.io.File

fun sandSlap(part: Int): Int {
    var puzzleInput = mutableListOf<Brick>()
	
    var id = 0
	  File("day2322_puzzle_input.txt").forEachLine {
      var (minV, maxV) = it.split("~")
      
        puzzleInput.add(Brick(id, minV.split(",")[0].toInt(), maxV.split(",")[0].toInt(), minV.split(",")[1].toInt(), maxV.split(",")[1].toInt(),  minV.split(",")[2].toInt(), maxV.split(",")[2].toInt()))
        id+=1
	}
     
    //puzzleInput.forEach {
    //    println(it)
    //}
    //println("--------------------")

    // #1 all bricks in snapshot now available
    // #2 condense snapshot by trying to move a brick down, iterate until no brick is able to move down any more
    //    (guess this could be more efficent, if puzzleinput will be sorted by height of bricks)
    
    var move = true
    var supportMap = mutableMapOf<Int, MutableList<Int>>()
    while (move) {//for (k in 0..5) {// while there is movement
        move = false
        supportMap.clear()
        for (i in 0..puzzleInput.size-1) {
            var collision = false
            var brick1 = puzzleInput[i]
            supportMap.put(brick1.id, mutableListOf())
            // println(brick1)
            if (brick1.zMin > 1) {
                    
                //println("new position created, check collision: $brick1")
                for (j in 0..puzzleInput.size-1) {
                    if (i != j) {
                        val brick2 = puzzleInput[j]
                        if (((brick2.xMin <= brick1.xMax) && (brick2.xMin >= brick1.xMin)) || ((brick1.xMin <= brick2.xMax) && (brick1.xMin >= brick2.xMin))) {
                            if (((brick2.yMin <= brick1.yMax) && (brick2.yMin >= brick1.yMin)) || ((brick1.yMin <= brick2.yMax) && (brick1.yMin >= brick2.yMin))) {
                                if (((brick2.zMin <= brick1.zMax-1) && (brick2.zMin >= brick1.zMin-1)) || ((brick1.zMin-1 <= brick2.zMax) && (brick1.zMin-1 >= brick2.zMin))) {
                                  //  println("Überschneidung brick ${brick1.id} und ${brick2.id}")
                                  //  println("   ${brick1}")
                                  //  println("   ${brick2}")
                                    collision = true
                                    var help = supportMap.getValue(brick1.id)
                                    help.add(brick2.id)
                                }              
                            }               
                        }
                    }
                }
            // if collison false: move brick
            //println("move brick? $collision")
            if (!collision) {
                puzzleInput[i].zMin -=1
                puzzleInput[i].zMax -=1
                move = true
            }
            }

        }

        //println()
        //puzzleInput.forEach {
        //    println(it)
        //} 
    }// no movement any more

    //println()
    //puzzleInput.forEach {
    //    println(it)
    //}

    // #3 check for each bricks by whom it is supported  -> why no set up a map each cycle and store all id and it's supports
    //println(supportMap)

    // #4 ceck for all supporting bricks if there is any brick which is additinally supported by another brick, count all bricks which are not single supporters
    var result = 0
    for (id in 0..supportMap.size-1) {
        var singleSupport = false
        for ((key,value) in supportMap) {
            if (value.contains(id) && value.size == 1) singleSupport = true 
        }
        if (!singleSupport) result +=1
    }


    return result
}

  

fun main() {

    var t1 = System.currentTimeMillis()

    println("--- Day 22: Sand Slabs ---")

    var solution1 = sandSlap(1)
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
