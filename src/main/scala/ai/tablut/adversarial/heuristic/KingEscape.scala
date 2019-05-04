package ai.tablut.adversarial.heuristic

import ai.tablut.state.Player.Player
import ai.tablut.state._

class KingEscape(gameContext: GameContext) extends HeuristicStrategy {
	override val minValue: Int = -81
	override val maxValue: Int = 81

	override def eval(state: State, player: Player): Int = {
		val kingPosition = state.findKing
		if (kingPosition.isEmpty)
			return maxValue

		val king = kingPosition.get
		val actionFactory = new ActionFactory(state, gameContext)
		val actions = actionFactory.allActions(king)
		actions.foldLeft[Int](0)((acc, a) => if (a.to.cellType == CellType.ESCAPE_POINT) acc + 1 else acc - 1)
	}
}
