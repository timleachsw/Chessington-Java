package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new LinkedList<>();

        // loop through each surrounding square
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Coordinates potential = new Coordinates(from.getRow() + i, from.getCol() + j);

                // is there a better way of doing this?
                PlayerColour other = colour == PlayerColour.WHITE ? PlayerColour.BLACK : PlayerColour.WHITE;

                // check valid move
                if (!(i == 0 && j == 0)  // can't move to self
                        && board.isInBounds(potential)  // can't leave board
                        && !board.isOwnPiece(potential, colour)  // can only move to empty space, or capture
                        && !board.isUnderAttackByTeam(potential, other)  // can't move into check
                ) {
                    allowedMoves.add(new Move(from, new Coordinates(from.getRow() + i, from.getCol() + j)));
                }
            }
        }

        return allowedMoves;
    }
}
