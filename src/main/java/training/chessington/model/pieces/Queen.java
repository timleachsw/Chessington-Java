package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.List;

public class Queen extends AbstractRayMover {
    public Queen(PlayerColour colour) {
        super(PieceType.QUEEN, colour);
    }

    @Override
    public List<Direction> getMoveDirections() {
        return List.of(
                Direction.NORTHEAST,
                Direction.NORTHWEST,
                Direction.SOUTHWEST,
                Direction.SOUTHEAST,
                Direction.NORTH,
                Direction.EAST,
                Direction.SOUTH,
                Direction.WEST
        );
    }
}
