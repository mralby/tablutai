package ai.tablut.state

import ai.tablut.state.CellContent.CellContent
import ai.tablut.state.CellType.CellType

trait BoardCell extends GameRulesComplied {
	/**
	  * Pair of integer representing the position of a cell
	  * within a [[ai.tablut.state.Board]].
	  * The first value must be represent the row index and
	  * the second value the column index by the implementation in order to maintain
	  * consistency to [[ai.tablut.state.Board]]
	  */
	val coords: (Int, Int)
	val cellType: CellType
	val cellContent: CellContent

	/**
	  * Board grid is a matrix. Considering the upper left corner as the origin point,
	  * x as the index of rows and y as the index of columns, this method transform
	  * coordinates (x,y) into the a string "ch1ch2" where ch1 is a lower case letter
	  * starting from "a" indexing the columns and ch2 is number starting from 1 indexing the rows.
	  * @return A string representing the coordinate using lower case letter for columns and number starting from 1 as rows
	  */
	def toHumanCoords: String
}