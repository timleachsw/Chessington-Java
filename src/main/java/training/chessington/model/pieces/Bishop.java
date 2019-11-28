package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Bishop extends AbstractPiece {
    public Bishop(PlayerColour colour) {
        super(PieceType.BISHOP, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new LinkedList<>();

        // march in each direction until we hit a piece or the edge of the board
        // if we hit an enemy piece, we can capture, but we must stop there i.e. we can't jump over an enemy piece
        int oldRow = from.getRow();
        int oldCol = from.getCol();
        Direction[] diagonals = {Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHWEST, Direction.SOUTHEAST};
        for (Direction direction: diagonals) {
            // generate ray in that direction
            List<Coordinates> ray = board.raycast(from, direction);

            for (Coordinates potential: ray) {
                // allowable move if empty or enemy piece
                if (!board.isOwnPiece(potential, colour)) {
                    allowedMoves.add(new Move(from, potential));
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
}
