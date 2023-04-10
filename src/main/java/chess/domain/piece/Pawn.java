package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.PawnMoveStrategy;
import chess.domain.strategy.piecemovestrategy.PieceType;

public final class Pawn extends AbstractPiece {

    private final PawnMoveStrategy pawnMoveStrategy;
    private Position position;

    public Pawn(final Color color, final Position position) {
        super(color);
        this.position = position;
        this.pawnMoveStrategy = PawnMoveStrategy.from(color);
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

    private void validateMovablePosition(final Piece target) {
        validateMovableToEmpty(target);
        validateMovableToEnemy(target);
    }

    private void validateMovableToEmpty(final Piece target) {
        if (target.isEmpty() && !pawnMoveStrategy.isMovableToEmpty(position, target.getPosition())) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다");
        }
    }

    private void validateMovableToEnemy(final Piece target) {
        if (isEnemy(target) && !pawnMoveStrategy.isMovableToEnemy(position, target.getPosition())) {
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
        return pawnMoveStrategy.getPieceType();
    }

    @Override
    public double getScore() {
        return getPieceType().getScore();
    }
}