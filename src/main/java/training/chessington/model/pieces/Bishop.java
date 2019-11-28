package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Bishop extends AbstractRayMover {
    public Bishop(PlayerColour colour) {
        super(PieceType.BISHOP, colour);
    }

    @Override
    protected List<Direction> getMoveDirections() {
        return List.of(
                Direction.NORTHEAST,
                Direction.NORTHWEST,
                Direction.SOUTHWEST,
                Direction.SOUTHEAST
        );
    }
}
