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
        int newRow = from.getRow() + (colour == PlayerColour.BLACK ? 1 : -1);

        // add move
        allowedMoves.add(new Move(from, new Coordinates(newRow, from.getCol())));
        return allowedMoves;
    }
}
