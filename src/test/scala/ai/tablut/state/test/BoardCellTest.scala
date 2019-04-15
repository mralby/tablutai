package ai.tablut.state.test

import ai.tablut.state._
import org.scalatest.WordSpec

class BoardCellTest extends WordSpec{


	"BoardCell" when {
		"using normal game rules" should{
			val factory = StateFacade.normalStateFactory()

			 "create human coordinates correctly" in{
				val cell = factory.createBoardCell(
					(3,4),
					CellContent.WHITE
				)

				 assert(cell.toHumanCoords == "e4")

				 val cell2 = factory.createBoardCell(
					 (6,6),
					 CellContent.EMPTY
				 )

				 assert(cell2.toHumanCoords == "g7")
			}

			"recognize its type" in {
				val cell = factory.createBoardCell((3,4), CellContent.WHITE)
				assert(cell.cellType == CellType.NOTHING)

				val castle = factory.createBoardCell((4,4), CellContent.WHITE)
				assert(castle.cellType == CellType.CASTLE)

				val camp = factory.createBoardCell((0,4), CellContent.BLACK)
				assert(camp.cellType == CellType.CAMP)

				val escape = factory.createBoardCell((0,1), CellContent.BLACK)
				assert(escape.cellType == CellType.ESCAPE_POINT)
			}

			"pattern match over InvalidBoardCell" in{
				val camp = factory.createBoardCell((0,4), CellContent.WHITE)
				camp match {
					case InvalidBoardCell(_, _, _) => succeed
					case _ => fail()
				}
			}
		}
	}
}