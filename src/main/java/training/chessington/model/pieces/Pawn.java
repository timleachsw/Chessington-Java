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
        ArrayList<Move> allowedMoves = new ArrayList<>();

        // white moves up one square, black moves down one square
        boolean isBlack = colour == PlayerColour.BLACK;
        int oldRow = from.getRow();
        int newRow = oldRow + (isBlack ? 1 : -1);

        // if piece standing in front, or at edge of board, can't move at all
        if (newRow < 0 || newRow > 7 || board.get(new Coordinates(newRow, from.getCol())) != null) {
            // empty move list (will do capturing next probably)
            return allowedMoves;
        }

        // add move
        allowedMoves.add(new Move(from, new Coordinates(newRow, from.getCol())));

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
