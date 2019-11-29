package training.chessington.model;

import org.assertj.core.api.AbstractAssert;
import training.chessington.model.PlayerColour;

public class BoardAssert extends AbstractAssert<BoardAssert, Board> {
    public BoardAssert(Board board) {
        super(board, BoardAssert.class);
    }

    public static BoardAssert assertThat(Board actual) {
        return new BoardAssert(actual);
    }

    public BoardAssert isIdenticalTo(Board other) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordinates checking = new Coordinates(i, j);
                if (actual.get(checking) != other.get(checking)) {
                    failWithMessage(String.format("Boards are different at (%1$d, %2$d):\n" +
                            "actual.get(%1$d, %2$d) = %3$s\n" +
                            " other.get(%1$d, %2$d) = %4$s\n", i, j, actual.get(checking), other.get(checking)));
                }
            }
        }

        return this;
    }
}
