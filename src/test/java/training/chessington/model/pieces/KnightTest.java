package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KnightTest {
    @Test
    public void knightMovesInTheirOwnWay() {
        // arrange
        Board board = Board.empty();
        Knight knight = new Knight(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, knight);
        Move[] allowedMoves = {
                new Move(coords, coords.plus(1, 2)),
                new Move(coords, coords.plus(2, 1)),
                new Move(coords, coords.plus(-1, 2)),
                new Move(coords, coords.plus(-2, 1)),
                new Move(coords, coords.plus(1, -2)),
                new Move(coords, coords.plus(2, -1)),
                new Move(coords, coords.plus(-1, -2)),
                new Move(coords, coords.plus(-2, -1)),
        };

        // act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void knightTopLeft() {
        // arrange
        Board board = Board.empty();
        Knight knight = new Knight(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 0);
        board.placePiece(coords, knight);
        Move[] allowedMoves = {
                new Move(coords, coords.plus(1, 2)),
                new Move(coords, coords.plus(2, 1)),
        };

        // act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void knightTopRight() {
        // arrange
        Board board = Board.empty();
        Knight knight = new Knight(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 7);
        board.placePiece(coords, knight);
        Move[] allowedMoves = {
                new Move(coords, coords.plus(1, -2)),
                new Move(coords, coords.plus(2, -1)),
        };

        // act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void knightBottomLeft() {
        // arrange
        Board board = Board.empty();
        Knight knight = new Knight(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(7, 0);
        board.placePiece(coords, knight);
        Move[] allowedMoves = {
                new Move(coords, coords.plus(-1, 2)),
                new Move(coords, coords.plus(-2, 1)),
        };

        // act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void knightBottomRight() {
        // arrange
        Board board = Board.empty();
        Knight knight = new Knight(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(7, 7);
        board.placePiece(coords, knight);
        Move[] allowedMoves = {
                new Move(coords, coords.plus(-1, -2)),
                new Move(coords, coords.plus(-2, -1)),
        };

        // act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void knightCanJumpPieces() {
        // arrange
        Board board = Board.empty();
        Knight knight = new Knight(PlayerColour.WHITE);
        Pawn pawn1 = new Pawn(PlayerColour.WHITE);
        Pawn pawn2 = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        Coordinates coords1 = coords.plus(1, 1);
        Coordinates coords2 = coords.plus(1, -1);
        board.placePiece(coords, knight);
        board.placePiece(coords1, pawn1);
        board.placePiece(coords2, pawn2);
        Move[] allowedMoves = {
                new Move(coords, coords.plus(1, 2)),
                new Move(coords, coords.plus(2, 1)),
                new Move(coords, coords.plus(-1, 2)),
                new Move(coords, coords.plus(-2, 1)),
                new Move(coords, coords.plus(1, -2)),
                new Move(coords, coords.plus(2, -1)),
                new Move(coords, coords.plus(-1, -2)),
                new Move(coords, coords.plus(-2, -1)),
        };

        // act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void knightCanCapture() {
        // arrange
        Board board = Board.empty();
        Knight knight = new Knight(PlayerColour.WHITE);
        Pawn pawn = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        Coordinates coords1 = coords.plus(1, 2);
        board.placePiece(coords, knight);
        board.placePiece(coords1, pawn);
        Move[] allowedMoves = {
                new Move(coords, coords.plus(1, 2)),
                new Move(coords, coords.plus(2, 1)),
                new Move(coords, coords.plus(-1, 2)),
                new Move(coords, coords.plus(-2, 1)),
                new Move(coords, coords.plus(1, -2)),
                new Move(coords, coords.plus(2, -1)),
                new Move(coords, coords.plus(-1, -2)),
                new Move(coords, coords.plus(-2, -1)),
        };

        // act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }

    @Test
    public void knightBlockedByOwnPiece() {
        // arrange
        Board board = Board.empty();
        Knight knight = new Knight(PlayerColour.WHITE);
        Pawn pawn = new Pawn(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        Coordinates coords1 = coords.plus(1, 2);
        board.placePiece(coords, knight);
        board.placePiece(coords1, pawn);
        Move[] allowedMoves = {
                new Move(coords, coords.plus(2, 1)),
                new Move(coords, coords.plus(-1, 2)),
                new Move(coords, coords.plus(-2, 1)),
                new Move(coords, coords.plus(1, -2)),
                new Move(coords, coords.plus(2, -1)),
                new Move(coords, coords.plus(-1, -2)),
                new Move(coords, coords.plus(-2, -1)),
        };

        // act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // assert
        assertThat(moves).containsExactlyInAnyOrder(allowedMoves);
    }
}
