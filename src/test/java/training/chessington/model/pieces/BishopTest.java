package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class BishopTest {
    @Test
    public void bishopMovesDiagonally() {
        // arrange
        Board board = Board.empty();
        Bishop bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, bishop);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(7, 7)),
                new Move(coords, new Coordinates(6, 6)),
                new Move(coords, new Coordinates(5, 5)),
                new Move(coords, new Coordinates(3, 3)),
                new Move(coords, new Coordinates(2, 2)),
                new Move(coords, new Coordinates(1, 1)),
                new Move(coords, new Coordinates(0, 0)),
                new Move(coords, new Coordinates(7, 1)),
                new Move(coords, new Coordinates(6, 2)),
                new Move(coords, new Coordinates(5, 3)),
                new Move(coords, new Coordinates(3, 5)),
                new Move(coords, new Coordinates(2, 6)),
                new Move(coords, new Coordinates(1, 7))
        };

        // act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void bishopTopLeft() {
        // arrange
        Board board = Board.empty();
        Bishop bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 0);
        board.placePiece(coords, bishop);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(7, 7)),
                new Move(coords, new Coordinates(6, 6)),
                new Move(coords, new Coordinates(5, 5)),
                new Move(coords, new Coordinates(4, 4)),
                new Move(coords, new Coordinates(3, 3)),
                new Move(coords, new Coordinates(2, 2)),
                new Move(coords, new Coordinates(1, 1))
        };

        // act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void bishopTopRight() {
        // arrange
        Board board = Board.empty();
        Bishop bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 7);
        board.placePiece(coords, bishop);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(7, 0)),
                new Move(coords, new Coordinates(6, 1)),
                new Move(coords, new Coordinates(5, 2)),
                new Move(coords, new Coordinates(4, 3)),
                new Move(coords, new Coordinates(3, 4)),
                new Move(coords, new Coordinates(2, 5)),
                new Move(coords, new Coordinates(1, 6))
        };

        // act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void bishopBottomLeft() {
        // arrange
        Board board = Board.empty();
        Bishop bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(7, 0);
        board.placePiece(coords, bishop);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(6, 1)),
                new Move(coords, new Coordinates(5, 2)),
                new Move(coords, new Coordinates(4, 3)),
                new Move(coords, new Coordinates(3, 4)),
                new Move(coords, new Coordinates(2, 5)),
                new Move(coords, new Coordinates(1, 6)),
                new Move(coords, new Coordinates(0, 7))
        };

        // act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void bishopBottomRight() {
        // arrange
        Board board = Board.empty();
        Bishop bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(7, 7);
        board.placePiece(coords, bishop);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(6, 6)),
                new Move(coords, new Coordinates(5, 5)),
                new Move(coords, new Coordinates(4, 4)),
                new Move(coords, new Coordinates(3, 3)),
                new Move(coords, new Coordinates(2, 2)),
                new Move(coords, new Coordinates(1, 1)),
                new Move(coords, new Coordinates(0, 0))
        };

        // act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }
}
