package training.chessington.model;

import org.junit.Test;
import training.chessington.model.pieces.King;
import training.chessington.model.pieces.Piece;

import java.util.List;

import static training.chessington.model.pieces.Piece.PieceType.PAWN;
import static training.chessington.model.pieces.PieceAssert.*;
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
}