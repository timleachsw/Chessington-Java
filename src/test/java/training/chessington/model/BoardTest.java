package training.chessington.model;

import org.junit.Test;
import training.chessington.model.pieces.*;


import static training.chessington.model.pieces.Piece.PieceType.PAWN;
import static training.chessington.model.pieces.PieceAssert.*;
import static training.chessington.model.BoardAssert.*;
import static org.assertj.core.api.Assertions.*;

public class BoardTest {
    @Test
    public void newBoardHasWhitePiecesAtBottom() {
        // Arrange
        Board board = Board.forNewGame();

        // Act
        Piece piece = board.get(new Coordinates(7, 0));

        // Assert
        assertThat(piece).isColour(PlayerColour.WHITE);
    }

    @Test
    public void newBoardHasBlackPiecesAtTop() {
        // Arrange
        Board board = Board.forNewGame();

        // Act
        Piece piece = board.get(new Coordinates(0, 0));

        // Assert
        assertThat(piece).isColour(PlayerColour.BLACK);
    }

    @Test
    public void canMovePiecesOnBoard() {
        // Arrange
        Board board = Board.forNewGame();

        Coordinates from = new Coordinates(6, 0);
        Coordinates to = new Coordinates(4, 4);

        // Act
        board.move(from, to);

        // Assert
        assertThat(board.get(from)).isNull();
        assertThat(board.get(to)).isColour(PlayerColour.WHITE).isPiece(PAWN);
    }

    @Test
    public void canTellIfSquaresAreUnderAttack() {
        // arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);

        // act
        board.placePiece(coords, king);
        boolean underAttack = board.isUnderAttackByTeam(
                new Coordinates(3, 3), PlayerColour.WHITE
        );

        // assert
        assertThat(underAttack).isTrue();
    }

    @Test
    public void cloneWorksCorrectly() {
        // arrange
        Board originalBoard = Board.empty();
        Piece[] pieces = {
                new King(PlayerColour.WHITE),
                new Bishop(PlayerColour.WHITE),
                new Knight(PlayerColour.WHITE),
                new King(PlayerColour.BLACK),
                new Queen(PlayerColour.BLACK),
                new Rook(PlayerColour.BLACK)
        };
        Coordinates[] coords = {
                new Coordinates(7, 4),
                new Coordinates(5, 6),
                new Coordinates(4, 2),
                new Coordinates(0, 4),
                new Coordinates(4, 4),
                new Coordinates(3, 0)
        };
        for (int i = 0; i < 6; i++) {
            originalBoard.placePiece(coords[i], pieces[i]);
        }

        // act
        Board newBoard = originalBoard.clone();

        // assert
        assertThat(newBoard).isIdenticalTo(originalBoard);
    }

    @Test
    public void movePreviewWorksCorrectly() {
        // arrange
        Board originalBoard = Board.empty();
        Piece[] pieces = {
                new King(PlayerColour.WHITE),
                new Bishop(PlayerColour.WHITE),
                new Knight(PlayerColour.WHITE),
                new King(PlayerColour.BLACK),
                new Queen(PlayerColour.BLACK),
                new Rook(PlayerColour.BLACK)
        };
        Coordinates[] coords = {
                new Coordinates(7, 4),
                new Coordinates(5, 6),
                new Coordinates(4, 2),
                new Coordinates(0, 4),
                new Coordinates(4, 4),
                new Coordinates(3, 0)
        };
        for (int i = 0; i < 6; i++) {
            originalBoard.placePiece(coords[i], pieces[i]);
        }
        Move move = new Move(coords[0], coords[0].plus(-1, 0));

        // act
        Board newBoard = originalBoard.previewMove(move);
        originalBoard.move(move);

        // assert
        assertThat(newBoard).isIdenticalTo(originalBoard);
    }
}