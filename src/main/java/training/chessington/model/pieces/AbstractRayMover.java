package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.LinkedList;
import java.util.List;

// a piece that moves in rays i.e. bishop, rook, and queen
public abstract class AbstractRayMover extends AbstractPiece {
    protected AbstractRayMover(PieceType type, PlayerColour colour) {
        super(type, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new LinkedList<>();

        // march in each direction until we hit a piece or the edge of the board
        // if we hit an enemy piece, we can capture, but we must stop there i.e. we can't jump over an enemy piece
        int oldRow = from.getRow();
        int oldCol = from.getCol();
        for (Direction direction: getMoveDirections()) {
            // generate ray in that direction
            List<Coordinates> ray = board.raycast(from, direction);

            for (Coordinates potential: ray) {
                // allowable move if empty or enemy piece
                if (!board.isOwnPiece(potential, colour)) {
                    // would this move result in check?
                    Move move = new Move(from, potential);
                    if (!board.wouldResultInCheck(move, colour)) {
                        allowedMoves.add(move);
                    }
                } else {
                    break;
                }

                // if it's an enemy piece, however, we have to stop there
                if (board.isEnemyPiece(potential, colour)) {
                    break;
                }
            }
        }

        return allowedMoves;
    }

    protected abstract List<Direction> getMoveDirections();
}
