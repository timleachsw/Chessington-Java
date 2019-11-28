package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KingTest {
    @Test
    public void KingMovesProperlyFromCentre() {
        // arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(3, 3)),
                new Move(coords, new Coordinates(3, 4)),
                new Move(coords, new Coordinates(3, 5)),
                new Move(coords, new Coordinates(4, 3)),
                new Move(coords, new Coordinates(4, 5)),
                new Move(coords, new Coordinates(5, 3)),
                new Move(coords, new Coordinates(5, 4)),
                new Move(coords, new Coordinates(5, 5))
        };

        // act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void KingInTopLeft() {
        // arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 0);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(0, 1)),
                new Move(coords, new Coordinates(1, 1)),
                new Move(coords, new Coordinates(1, 0))
        };

        // act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void KingInTopRight() {
        // arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 7);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(0, 6)),
                new Move(coords, new Coordinates(1, 6)),
                new Move(coords, new Coordinates(1, 7))
        };

        // act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void KingInBottomLeft() {
        // arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(7, 0);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(7, 1)),
                new Move(coords, new Coordinates(6, 1)),
                new Move(coords, new Coordinates(6, 0))
        };

        // act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void KingInBottomRight() {
        // arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(7, 7);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(7, 6)),
                new Move(coords, new Coordinates(6, 6)),
                new Move(coords, new Coordinates(6, 7))
        };

        // act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }
}
