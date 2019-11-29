package training.chessington.model;

import training.chessington.model.pieces.*;

import java.util.LinkedList;
import java.util.List;

public class Board {

    private Piece[][] board = new Piece[8][8];

    private Board() {
    }

    public static Board forNewGame() {
        Board board = new Board();
        board.setBackRow(0, PlayerColour.BLACK);
        board.setBackRow(7, PlayerColour.WHITE);

        for (int col = 0; col < 8; col++) {
            board.board[1][col] = new Pawn(PlayerColour.BLACK);
            board.board[6][col] = new Pawn(PlayerColour.WHITE);
        }

        return board;
    }

    public static Board empty() {
        return new Board();
    }

    private void setBackRow(int rowIndex, PlayerColour colour) {
        board[rowIndex][0] = new Rook(colour);
        board[rowIndex][1] = new Knight(colour);
        board[rowIndex][2] = new Bishop(colour);
        board[rowIndex][3] = new Queen(colour);
        board[rowIndex][4] = new King(colour);
        board[rowIndex][5] = new Bishop(colour);
        board[rowIndex][6] = new Knight(colour);
        board[rowIndex][7] = new Rook(colour);
    }

    public Piece get(Coordinates coords) {
        return board[coords.getRow()][coords.getCol()];
    }

    public void move(Coordinates from, Coordinates to) {
        board[to.getRow()][to.getCol()] = board[from.getRow()][from.getCol()];
        board[from.getRow()][from.getCol()] = null;
    }

    public void move(Move move) {
        move(move.getFrom(), move.getTo());
    }

    public void placePiece(Coordinates coords, Piece piece) {
        board[coords.getRow()][coords.getCol()] = piece;
    }

    public boolean isInBounds(Coordinates coords) {  // not static because in theory could have differently sized boards
        return !(coords.getRow() > 7 || coords.getRow() < 0 || coords.getCol() > 7 || coords.getCol() < 0);
    }

    public boolean isEmpty(Coordinates coords) {
        return get(coords) == null;
    }

    public boolean isEnemyPiece(Coordinates coords, PlayerColour colour) {
        // first, check it's not empty
        if (isEmpty(coords)) {
            return false;
        }
        // then, check it's the other colour
        return get(coords).getColour() != colour;
    }

    public boolean isOwnPiece(Coordinates coords, PlayerColour colour) {
        return !(isEmpty(coords) || isEnemyPiece(coords, colour));
    }

    public boolean isUnderAttackByTeam(Coordinates coords, PlayerColour colour) {
        // note: this doesn't return accurate results if the square given is empty, because of pawns' weird move patterns
        // iterate through all squares on board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordinates current = new Coordinates(i, j);
                Piece currentPiece = get(current);

                // is there a piece here?
                if (currentPiece == null) {
                    continue;
                }

                // is it of the relevant team?
                if (currentPiece.getColour() == colour) {
                    // do this piece's allowable moves include the relevant square?
                    if (currentPiece.getPotentialCaptures(current, this).contains(new Move(current, coords))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public List<Coordinates> raycast(Coordinates from, Direction direction) {
        List<Coordinates> ray = new LinkedList<>();

        // generate unit (under infinity-norm) vector in direction
        int[] dirVector = {0, 0};
        if (direction == Direction.NORTHWEST || direction == Direction.NORTH || direction == Direction.NORTHEAST) {
            dirVector[1] = -1;
        }
        if (direction == Direction.SOUTHWEST || direction == Direction.SOUTH || direction == Direction.SOUTHEAST) {
            dirVector[1] = 1;
        }
        if (direction == Direction.NORTHWEST || direction == Direction.WEST || direction == Direction.SOUTHWEST) {
            dirVector[0] = -1;
        }
        if (direction == Direction.NORTHEAST || direction == Direction.EAST || direction == Direction.SOUTHEAST) {
            dirVector[0] = 1;
        }

        // march in that direction until we're off the board
        for (int i = 1; i < 8; i++) {
            // next coordinate
            Coordinates next = from.plus(i * dirVector[0], i * dirVector[1]);

            // off board?
            if (!isInBounds(next)) {
                break;
            }

            // if not, add
            ray.add(next);
        }

        return ray;
    }

    public Board clone() {
        Board newBoard = Board.empty();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordinates coords = new Coordinates(i, j);
                newBoard.placePiece(coords, get(coords));
            }
        }

        return newBoard;
    }

    public Board previewMove(Move move) {
        Board preview = clone();
        preview.move(move);
        return preview;
    }

    public boolean isInCheck(PlayerColour colour) {
        // find the square with the king in
        Coordinates kingCoords = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordinates test = new Coordinates(i, j);
                Piece testPiece = get(test);
                if (testPiece != null) {
                    if (testPiece.getType() == Piece.PieceType.KING && testPiece.getColour() == colour) {
                        kingCoords = test.plus(0, 0);  // hacky way of cloning
                    }
                }
            }
        }

        if (kingCoords == null) {
            throw new RuntimeException("King is somehow missing from board.");
        }

        PlayerColour other = colour == PlayerColour.BLACK ? PlayerColour.WHITE : PlayerColour.BLACK;

        // can any pieces attack him?
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordinates test = new Coordinates(i, j);
                Piece testPiece = get(test);
                if (testPiece != null) {
                    if (testPiece.getColour() == other && testPiece.getPotentialCaptures(test, this)
                            .contains(new Move(test, kingCoords))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
