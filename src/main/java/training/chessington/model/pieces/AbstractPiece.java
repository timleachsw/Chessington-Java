package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

public abstract class AbstractPiece implements Piece {

    private final Piece.PieceType type;
    protected final PlayerColour colour;

    AbstractPiece(Piece.PieceType type, PlayerColour colour) {
        this.type = type;
        this.colour = colour;
    }

    @Override
    public List<Move> getPotentialCaptures(Coordinates from, Board board) {
        return getAllowedMoves(from, board);
    }

    @Override
    public Piece.PieceType getType() {
        return type;
    }

    @Override
    public PlayerColour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour.toString() + " " + type.toString();
    }
}
