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
        int newRow = oldRow + (isBlack ? 1 : -1);

        // if square in front empty, can move one space ahead
        if (board.get(new Coordinates(newRow, from.getCol())) == null) {
            allowedMoves.add(new Move(from, new Coordinates(newRow, from.getCol())));
        }

        // if it hasn't moved yet (i.e. is on row 1 for black, 6 for white)
        // note - doesn't check move history but row, therefore is assuming the board has been setup
        // for a normal chess game
        if (from.getRow() == (isBlack ? 1 : 6)) {
            // is on start row therefore hasn't moved yet
            newRow = oldRow + (isBlack ? 2 : -2);

            // add move
            allowedMoves.add(new Move(from, new Coordinates(newRow, from.getCol())));
        }

        return allowedMoves;
    }
}
