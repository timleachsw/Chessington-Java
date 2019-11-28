package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        // this boolean comes in useful
        boolean isBlack = colour == PlayerColour.BLACK;

        // result to return
        ArrayList<Move> allowedMoves = new ArrayList<>();

        // can't move if at edge of board
        if (from.getRow() == (isBlack ? 7 : 0)) {
            return allowedMoves;
        }

        // white moves up one square, black moves down one square
        int oldRow = from.getRow();
        int oldCol = from.getCol();
        int direction = (isBlack ? 1 : -1);  // the direction this piece moves in

        // if square in front empty, can move one (or two) spaces ahead
        if (board.get(new Coordinates(oldRow + direction, oldCol)) == null) {
            allowedMoves.add(new Move(from, new Coordinates(oldRow + direction, oldCol)));

            // if it hasn't moved yet (i.e. is on row 1 for black, 6 for white) and space 2 ahead is clear
            // note - doesn't check move history but row, therefore is assuming the board has been setup
            // for a normal chess game
            if (from.getRow() == (isBlack ? 1 : 6) && board.get(new Coordinates(oldRow + direction * 2, oldCol)) == null) {
                allowedMoves.add(new Move(from, new Coordinates(oldRow + direction * 2, oldCol)));
            }
        }

        // can capture diagonally, check row 1 ahead and column on either side
        if (oldCol + 1 <= 7) {
            Coordinates aheadLeft = new Coordinates(oldRow + direction, oldCol + 1);
            Piece pieceAheadLeft = board.get(aheadLeft);
            if (pieceAheadLeft != null && pieceAheadLeft.getColour() != colour /* must be a different colour to capture! */) {
                allowedMoves.add(new Move(from, aheadLeft));
            }
        }
        if (oldCol - 1 >= 0) {
            Coordinates aheadRight = new Coordinates(oldRow + direction, oldCol - 1);
            Piece pieceAheadRight = board.get(aheadRight);
            if (pieceAheadRight != null && pieceAheadRight.getColour() != colour) {
                allowedMoves.add(new Move(from, aheadRight));
            }
        }

        return allowedMoves;
    }
}
