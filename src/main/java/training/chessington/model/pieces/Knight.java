package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Knight extends AbstractPiece {
    public Knight(PlayerColour colour) {
        super(PieceType.KNIGHT, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new LinkedList<>();

        // generate options
        List<Coordinates> potentialMoves = List.of(
                from.plus(1, 2),
                from.plus(2, 1),
                from.plus(-1, 2),
                from.plus(-2, 1),
                from.plus(1, -2),
                from.plus(2, -1),
                from.plus(-1, -2),
                from.plus(-2, -1)
        );

        // iterate through, adding to list if allowed
        for (Coordinates potential: potentialMoves) {
            Move move = new Move(from, potential);
            if (board.isInBounds(potential) && !board.isOwnPiece(potential, colour) && !board.wouldResultInCheck(move, colour)) {
                allowedMoves.add(move);
            }
        }

        return allowedMoves;
    }
}
