# aoc-2023
My solutions for Advent of Code 2023

My learnings:

- how to get hold on matchResults:
  var matchPartNumber = patternPartNumber.findAll(it)
	matchPartNumber.forEach {
	  var rangeCheck = IntRange(it.range.first-1, it.range.last + 1)

  var winningNumbers = pattern.findAll(it).map { it.value }.toList()

  -> either iterate to mathResult or map it to value/range and convert to list

  
