package ai.tablut.adversarial.heuristic
import ai.tablut.state.CellContent._
import ai.tablut.state.Player.Player
import ai.tablut.state.{Player, State, StateFactory}

class HFNearKing(stateFactory: StateFactory) extends HeuristicFunction {

	private def distance(black: (Int, Int), king: (Int, Int)): Double =
		scala.math.sqrt(math.pow(king._1 - black._1, 2) + math.pow(king._2 - black._2, 2))

	override def eval(state: State, player: Player): Double = {
		val findKing = state.findKing
		if (findKing.isEmpty)
			return 1
		val king = findKing.get
		val blacks = state.getCellsWithFilter(c => c.cellContent == BLACK)

		val min = distance((0,0), (0,1))
		val max = distance((0,0), (7,7)) * stateFactory.context.maxBlacks

		val start = if (player == Player.BLACK) min else stateFactory.createInitialState()
			.getCellsWithFilter(c => c.cellContent == BLACK)
			.foldLeft[Double](0){(acc, black) =>
			acc + distance(black.coords, king.coords)
		}

		val eval = blacks.foldLeft[Double](start.toDouble){(acc, black) =>
			acc + distance(black.coords, king.coords)
		}

		Normalizer.createNormalizer(min, max)(if (player == Player.BLACK) max - eval else eval - start)
	}
}
