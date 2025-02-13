// sudo apt-get update && sudo apt-get install kotlin
// kotlinc day2324_1_2.kt -include-runtime -d day2324_1_2.jar && java -jar -Xss515m day2324_1_2.jar

import java.io.File
import kotlin.math.*

fun aocDay2324(part: Int): Long {

    var px = mutableListOf<Long>()
    var py = mutableListOf<Long>()
    var pz = mutableListOf<Long>()
    var vx = mutableListOf<Long>()
    var vy = mutableListOf<Long>()
    var vz = mutableListOf<Long>()

    File("day2324_puzzle_input.txt").forEachLine {
      var pos = it.substringBefore(" @")
      var vel = it.substringAfter("@ ")
      px.add(pos.split(", ")[0].toLong())
      py.add(pos.split(", ")[1].toLong())
      pz.add(pos.split(", ")[2].toLong())
      vx.add(vel.split(", ")[0].toLong())
      vy.add(vel.split(", ")[1].toLong())
      vz.add(vel.split(", ")[2].toLong())
    }

    var span = 10L

    // determine x coordinate
    var pxMin = px.min()!!
    var pxMax = px.max()!!
    var vxMin = vx.min()!!
    var vxMax = vx.max()!!    
    var posParametersX = Pair(0L,0L)

    for (x in pxMin-span.. pxMax+span) {
      for (v in vxMin-span..vxMax+span) {
        var hitsAllX = 0
        for (i in 0..px.size-1) {
          if (vx[i] != v) {
            if ((x-px[i]) % (vx[i]-v) == 0L && (x-px[i]) / (vx[i]-v) > 0L) hitsAllX += 1
          } else {
            if (x == px[i]) hitsAllX +=1
          }
        }
        if (hitsAllX == px.size) {
          posParametersX = Pair(x,v)
          println("x paramteters found $posParametersX")
          break
        }
      }
    }

        // determine y coordinate
        var pyMin = py.min()!!
        var pyMax = py.max()!!
        var vyMin = vy.min()!!
        var vyMax = vy.max()!!    
        var posParametersY = Pair(0L,0L)
    
        for (y in pyMin-span.. pyMax+span) {
          for (v in vyMin-span..vyMax+span) {
            var hitsAllY = 0
            for (i in 0..py.size-1) {
              if (vy[i] != v) {
                if ((y-py[i]) % (vy[i]-v) == 0L && (y-py[i]) / (vy[i]-v) > 0L) hitsAllY += 1
              } else {
                if (y == py[i]) hitsAllY+=1
              }
            }
            if (hitsAllY == py.size) {
              posParametersY = Pair(y,v)
              println("y paramteters found $posParametersY")
              break
            }
          }
        }

        // determine z coordinate
        var pzMin = pz.min()!!
        var pzMax = pz.max()!!
        var vzMin = vz.min()!!
        var vzMax = vz.max()!!    
        var posParametersZ = Pair(0L,0L)
    
        for (z in pzMin-span.. pzMax+span) {
          for (v in vzMin-span..vzMax+span) {
            var hitsAllZ = 0
            for (i in 0..pz.size-1) {
              if (vz[i] != v) {
                if ((z-pz[i]) % (vz[i]-v) == 0L && (z-pz[i]) / (vz[i]-v) > 0L) hitsAllZ += 1
              } else {
                if (z == pz[i]) hitsAllZ +=1
              }
            }
            if (hitsAllZ == pz.size) {
              posParametersZ = Pair(z,v)
              println("z paramteters found $posParametersZ")
              break
            }
          }
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
