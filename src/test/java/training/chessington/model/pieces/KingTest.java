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
    public void kingMovesProperlyFromCentre() {
        // arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, king);
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
    public void kingInTopLeft() {
        // arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 0);
        board.placePiece(coords, king);
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
    public void kingInTopRight() {
        // arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 7);
        board.placePiece(coords, king);
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
    public void kingInBottomLeft() {
        // arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(7, 0);
        board.placePiece(coords, king);
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
    public void kingInBottomRight() {
        // arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(7, 7);
        board.placePiece(coords, king);
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

    @Test
    public void kingAdjacentToOwnPiece() {
        // arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates kingCoords = new Coordinates(4, 4);
        Coordinates pawnCoords = new Coordinates(4, 5);
        board.placePiece(kingCoords, king);
        board.placePiece(pawnCoords, pawn);
        Move[] allowedMoves = {
                new Move(kingCoords, new Coordinates(3, 3)),
                new Move(kingCoords, new Coordinates(3, 4)),
                new Move(kingCoords, new Coordinates(3, 5)),
                new Move(kingCoords, new Coordinates(4, 3)),
                new Move(kingCoords, new Coordinates(5, 3)),
                new Move(kingCoords, new Coordinates(5, 4)),
                new Move(kingCoords, new Coordinates(5, 5))
        };

        // act
        List<Move> moves = king.getAllowedMoves(kingCoords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void kingAdjacentToEnemyPiece() {
        // arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Piece pawn = new Pawn(PlayerColour.BLACK);
        Coordinates kingCoords = new Coordinates(4, 4);
        Coordinates pawnCoords = new Coordinates(4, 5);
        board.placePiece(kingCoords, king);
        board.placePiece(pawnCoords, pawn);
        Move[] allowedMoves = {
                new Move(kingCoords, new Coordinates(3, 3)),
                new Move(kingCoords, new Coordinates(3, 4)),
                new Move(kingCoords, new Coordinates(3, 5)),
                new Move(kingCoords, new Coordinates(4, 5)),
                new Move(kingCoords, new Coordinates(4, 3)),
                new Move(kingCoords, new Coordinates(5, 3)),
                new Move(kingCoords, new Coordinates(5, 4)),
                new Move(kingCoords, new Coordinates(5, 5))
        };

        // act
        List<Move> moves = king.getAllowedMoves(kingCoords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void kingCannotMoveIntoCheck() {
        // arrange
        Board board = Board.empty();
        King king = new King(PlayerColour.WHITE);
        Queen queen = new Queen(PlayerColour.BLACK);
        Coordinates kingCoords = new Coordinates(7, 4);
        Coordinates queenCoords = new Coordinates(0, 3);
        Move[] allowableMoves = {
                new Move(kingCoords, kingCoords.plus(-1, 0)),
                new Move(kingCoords, kingCoords.plus(-1, 1)),
                new Move(kingCoords, kingCoords.plus(0, 1))
        };

        // act
        board.placePiece(kingCoords, king);
        board.placePiece(queenCoords, queen);
        List<Move> moves = king.getAllowedMoves(kingCoords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowableMoves);
    }
}
