# aoc-2023
My solutions for Advent of Code 2023

My learnings:

- how to get hold on matchResults:
  var matchPartNumber = patternPartNumber.findAll(it)
	matchPartNumber.forEach {
	  var rangeCheck = IntRange(it.range.first-1, it.range.last + 1)

  var winningNumbers = pattern.findAll(it).map { it.value }.toList()

  -> either iterate to mathResult or map it to value/range and convert to list

- using iterator / list iterator. Day2304 / Day1825:
  
  val wonCards = puzzle_input.toMutableList()
	val wonCardsIterator = wonCards.listIterator()

	while (wonCardsIterator.hasNext()) {
		var card = wonCardsIterator.next()
		var cardNumber = pattern.find(card.substringBefore(":"))!!.value.toInt()
		var winningNumbers = pattern.findAll(card.substringAfter(": ").split(" | ")[0]).map { it.value }.toList()
		var numbersYouHave = pattern.findAll(card.substringAfter(": ").split(" | ")[1]).map { it.value }.toList()
		var wins = (winningNumbers + numbersYouHave).size - (winningNumbers + numbersYouHave).distinct().size

		for (i in 0..wins - 1) {
			wonCardsIterator.add(puzzle_input[cardNumber + i])
			wonCardsIterator.previous()
		}
	}  
	-> iterator does not allow .add. This adds a new entry at the current positon
  	-> iterator jumps over added entry, so you have to perform a .previous if you want to continue at the new entry
