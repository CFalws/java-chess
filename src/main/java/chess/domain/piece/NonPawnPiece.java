package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.PieceMoveStrategy;
import chess.domain.strategy.piecemovestrategy.PieceType;

public final class NonPawnPiece extends AbstractPiece {

    private final PieceMoveStrategy pieceMoveStrategy;
    private Position position;

    public NonPawnPiece(final Color color, final Position position, final PieceMoveStrategy pieceMoveStrategy) {
        super(color);
        this.position = position;
        this.pieceMoveStrategy = pieceMoveStrategy;
    }

    @Override
    public void move(final Piece target) {
        validateMovable(target);

        position = target.getPosition();
    }

    private void validateMovable(final Piece target) {
        validateNotSamePosition(target);
        validateNotSameColor(target);
        validateMovablePosition(target);
    }

    private void validateNotSamePosition(final Piece target) {
        if (position.equals(target.getPosition())) {
            throw new IllegalArgumentException("가려고 하는 위치가 현재 위치와 같습니다");
        }
    }

    private void validateMovablePosition(Piece target) {
        if (!pieceMoveStrategy.isMovable(position, target.getPosition())) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다");
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public PieceType getPieceType() {
        return pieceMoveStrategy.getPieceType();
    }

    @Override
    public double getScore() {
        return getPieceType().getScore();
    }
}
