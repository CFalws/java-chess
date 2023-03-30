package chess.piece;

import chess.chessboard.Position;
import chess.chessboard.Side;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private static final List<Rook> blackRooks = new ArrayList<>();
    private static final List<Rook> whiteRooks = new ArrayList<>();
    private static final int NUMBER_OF_ROOKS_EACH_SIDE = 2;

    static {
        addRooks(blackRooks, Side.BLACK);
        addRooks(whiteRooks, Side.WHITE);
    }

    public Rook(final Side side) {
        super(side, PieceType.ROOK);
    }

    private static void addRooks(final List<Rook> rooks, final Side side) {
        for (int i = 0; i < NUMBER_OF_ROOKS_EACH_SIDE; i++) {
            rooks.add(new Rook(side));
        }
    }

    public static List<Rook> getRooksOf(final Side side) {
        if (side == Side.BLACK) {
            return List.copyOf(blackRooks);
        }
        return List.copyOf(whiteRooks);
    }

    @Override
    public boolean isValidMove(final Position from, final Position to, final Piece piece) {
        return isNotSameSide(piece) && isLine(from, to);
    }

    private boolean isLine(final Position from, final Position to) {
        from.validateNotSameSquare(to);

        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);
        return isLine(verticalDistance, horizontalDistance);
    }

    private boolean isLine(final int verticalDistance, final int horizontalDistance) {
        return verticalDistance == 0 || horizontalDistance == 0;
    }
}
