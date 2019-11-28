package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class RookTest {
    @Test
    public void rookMovesOrthogonally() {
        // arrange
        Board board = Board.empty();
        Rook rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, rook);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(4, 7)),
                new Move(coords, new Coordinates(4, 6)),
                new Move(coords, new Coordinates(4, 5)),
                new Move(coords, new Coordinates(4, 3)),
                new Move(coords, new Coordinates(4, 2)),
                new Move(coords, new Coordinates(4, 1)),
                new Move(coords, new Coordinates(4, 0)),
                new Move(coords, new Coordinates(7, 4)),
                new Move(coords, new Coordinates(6, 4)),
                new Move(coords, new Coordinates(5, 4)),
                new Move(coords, new Coordinates(3, 4)),
                new Move(coords, new Coordinates(2, 4)),
                new Move(coords, new Coordinates(1, 4)),
                new Move(coords, new Coordinates(0, 4))
        };

        // act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void rookTopLeft() {
        // arrange
        Board board = Board.empty();
        Rook rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 0);
        board.placePiece(coords, rook);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(0, 7)),
                new Move(coords, new Coordinates(0, 6)),
                new Move(coords, new Coordinates(0, 5)),
                new Move(coords, new Coordinates(0, 4)),
                new Move(coords, new Coordinates(0, 3)),
                new Move(coords, new Coordinates(0, 2)),
                new Move(coords, new Coordinates(0, 1)),
                new Move(coords, new Coordinates(7, 0)),
                new Move(coords, new Coordinates(6, 0)),
                new Move(coords, new Coordinates(5, 0)),
                new Move(coords, new Coordinates(4, 0)),
                new Move(coords, new Coordinates(3, 0)),
                new Move(coords, new Coordinates(2, 0)),
                new Move(coords, new Coordinates(1, 0))
        };

        // act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void rookTopRight() {
        // arrange
        Board board = Board.empty();
        Rook rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 7);
        board.placePiece(coords, rook);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(0, 6)),
                new Move(coords, new Coordinates(0, 5)),
                new Move(coords, new Coordinates(0, 4)),
                new Move(coords, new Coordinates(0, 3)),
                new Move(coords, new Coordinates(0, 2)),
                new Move(coords, new Coordinates(0, 1)),
                new Move(coords, new Coordinates(0, 0)),
                new Move(coords, new Coordinates(7, 7)),
                new Move(coords, new Coordinates(6, 7)),
                new Move(coords, new Coordinates(5, 7)),
                new Move(coords, new Coordinates(4, 7)),
                new Move(coords, new Coordinates(3, 7)),
                new Move(coords, new Coordinates(2, 7)),
                new Move(coords, new Coordinates(1, 7))
        };

        // act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void rookBottomLeft() {
        // arrange
        Board board = Board.empty();
        Rook rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(7, 0);
        board.placePiece(coords, rook);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(7, 7)),
                new Move(coords, new Coordinates(7, 6)),
                new Move(coords, new Coordinates(7, 5)),
                new Move(coords, new Coordinates(7, 4)),
                new Move(coords, new Coordinates(7, 3)),
                new Move(coords, new Coordinates(7, 2)),
                new Move(coords, new Coordinates(7, 1)),
                new Move(coords, new Coordinates(6, 0)),
                new Move(coords, new Coordinates(5, 0)),
                new Move(coords, new Coordinates(4, 0)),
                new Move(coords, new Coordinates(3, 0)),
                new Move(coords, new Coordinates(2, 0)),
                new Move(coords, new Coordinates(1, 0)),
                new Move(coords, new Coordinates(0, 0))
        };

        // act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void rookBottomRight() {
        // arrange
        Board board = Board.empty();
        Rook rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(7, 7);
        board.placePiece(coords, rook);
        Move[] allowedMoves = {
                new Move(coords, new Coordinates(7, 6)),
                new Move(coords, new Coordinates(7, 5)),
                new Move(coords, new Coordinates(7, 4)),
                new Move(coords, new Coordinates(7, 3)),
                new Move(coords, new Coordinates(7, 2)),
                new Move(coords, new Coordinates(7, 1)),
                new Move(coords, new Coordinates(7, 0)),
                new Move(coords, new Coordinates(6, 7)),
                new Move(coords, new Coordinates(5, 7)),
                new Move(coords, new Coordinates(4, 7)),
                new Move(coords, new Coordinates(3, 7)),
                new Move(coords, new Coordinates(2, 7)),
                new Move(coords, new Coordinates(1, 7)),
                new Move(coords, new Coordinates(0, 7))
        };

        // act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void rookBlockedByOwnPiece() {
        // arrange
        Board board = Board.empty();
        Rook rook = new Rook(PlayerColour.WHITE);
        Pawn pawn = new Pawn(PlayerColour.WHITE);
        Coordinates rookCoords = new Coordinates(4, 7);
        Coordinates pawnCoords = new Coordinates(4, 3);
        board.placePiece(rookCoords, rook);
        board.placePiece(pawnCoords, pawn);
        Move[] allowedMoves = {
                new Move(rookCoords, new Coordinates(4, 6)),
                new Move(rookCoords, new Coordinates(4, 5)),
                new Move(rookCoords, new Coordinates(4, 4)),
                new Move(rookCoords, new Coordinates(7, 7)),
                new Move(rookCoords, new Coordinates(6, 7)),
                new Move(rookCoords, new Coordinates(5, 7)),
                new Move(rookCoords, new Coordinates(3, 7)),
                new Move(rookCoords, new Coordinates(2, 7)),
                new Move(rookCoords, new Coordinates(1, 7)),
                new Move(rookCoords, new Coordinates(0, 7))
        };

        // act
        List<Move> moves = rook.getAllowedMoves(rookCoords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void rookBlockedByEnemyPiece() {
        // arrange
        Board board = Board.empty();
        Rook rook = new Rook(PlayerColour.WHITE);
        Pawn pawn = new Pawn(PlayerColour.BLACK);
        Coordinates rookCoords = new Coordinates(4, 7);
        Coordinates pawnCoords = new Coordinates(4, 3);
        board.placePiece(rookCoords, rook);
        board.placePiece(pawnCoords, pawn);
        Move[] allowedMoves = {
                new Move(rookCoords, new Coordinates(4, 6)),
                new Move(rookCoords, new Coordinates(4, 5)),
                new Move(rookCoords, new Coordinates(4, 4)),
                new Move(rookCoords, new Coordinates(4, 3)),
                new Move(rookCoords, new Coordinates(7, 7)),
                new Move(rookCoords, new Coordinates(6, 7)),
                new Move(rookCoords, new Coordinates(5, 7)),
                new Move(rookCoords, new Coordinates(3, 7)),
                new Move(rookCoords, new Coordinates(2, 7)),
                new Move(rookCoords, new Coordinates(1, 7)),
                new Move(rookCoords, new Coordinates(0, 7))
        };

        // act
        List<Move> moves = rook.getAllowedMoves(rookCoords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }
}
