package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.List;

public class Rook extends AbstractRayMover {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    protected List<Direction> getMoveDirections() {
        return List.of(
                Direction.NORTH,
                Direction.EAST,
                Direction.SOUTH,
                Direction.WEST
        );
    }
}
