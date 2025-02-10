// sudo apt-get update && sudo apt-get install kotlin
// kotlinc day2319_1_2.kt -include-runtime -d day2319_1_2.jar && java -jar -Xss515m day2319_1_2.jar

import java.io.File

var rules = mutableMapOf<String, String>()

fun aocDay2319part2(xMin: Int, xMax: Int, mMin: Int, mMax: Int, aMin: Int, aMax: Int, sMin: Int, sMax: Int, rule: String): Long {
     
    var result = 0L
    var xMi = xMin
    var xMa = xMax
    var mMi = mMin    
    var mMa = mMax
    var aMi = aMin
    var aMa = aMax
    var sMi = sMin
    var sMa = sMax
    
    if (rule == "A") {
         return  ((xMa -xMi + 1).toLong()*(mMa - mMi + 1).toLong()*(aMa -aMi + 1).toLong()*(sMa -sMi + 1).toLong())
    }
    if (rule == "R") return 0L
    
    var cond = rule.substringBefore(":")
    var trueRule = rule.substringAfter(":").substringBefore(",")
    var falseRule = rule.substringAfter(",")
    
    // true path
    when (cond.take(2)) {
        "x>" -> xMi = cond.drop(2).toInt()+1
        "x<" -> xMa = cond.drop(2).toInt()-1
        "m>" -> mMi = cond.drop(2).toInt()+1
        "m<" -> mMa = cond.drop(2).toInt()-1
        "a>" -> aMi = cond.drop(2).toInt()+1
        "a<" -> aMa = cond.drop(2).toInt()-1
        "s>" -> sMi = cond.drop(2).toInt()+1
        "s<" -> sMa = cond.drop(2).toInt()-1
    }
    if (!trueRule.contains(":") && trueRule != "A" && trueRule != "R") trueRule = rules.getValue(trueRule)
    result += aocDay2319part2(xMi, xMa, mMi, mMa, aMi, aMa, sMi, sMa, trueRule)
    
    xMi = xMin
    xMa = xMax
    mMi = mMin
    mMa = mMax
    aMi = aMin
    aMa = aMax
    sMi = sMin
    sMa = sMax
    
    // false path
        when (cond.take(2)) {
        "x<" -> xMi = cond.drop(2).toInt()
        "x>" -> xMa = cond.drop(2).toInt()
        "m<" -> mMi = cond.drop(2).toInt()
        "m>" -> mMa = cond.drop(2).toInt()
        "a<" -> aMi = cond.drop(2).toInt()
        "a>" -> aMa = cond.drop(2).toInt()
        "s<" -> sMi = cond.drop(2).toInt()
        "s>" -> sMa = cond.drop(2).toInt()
    }
   	if (!falseRule.contains(":") && falseRule != "A" && falseRule != "R") falseRule = rules.getValue(falseRule)
    result += aocDay2319part2(xMi, xMa, mMi, mMa, aMi, aMa, sMi, sMa, falseRule)
        
    return result
} 


fun main() {

    var t1 = System.currentTimeMillis()

    println("--- Day 19: Aplenty ---")

    File("day2319_puzzle_input.txt").forEachLine{
        if (it.take(1) != "{" && it != "")
        rules.put(it.split("{")[0], it.split("{")[1].dropLast(1))
    }

    //var solution1 = aocDay2319part1()
    //println("   the longest hike is $solution1 steps long")

    var solution2 = aocDay2319part2(1, 4000, 1, 4000, 1, 4000, 1, 4000, rules.getValue("in"))
    println("   $solution2 distinct combinations of ratings will be accepted ")

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
