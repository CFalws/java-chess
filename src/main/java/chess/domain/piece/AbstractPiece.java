package chess.domain.piece;

public abstract class AbstractPiece implements Piece {

    private final Color color;

    protected AbstractPiece(final Color color) {
        this.color = color;
    }

    protected void validateNotSameColor(final Piece target) {
        if (isFriend(target)) {
            throw new IllegalArgumentException("같은 색 기물은 잡을 수 없습니다");
        }
    }

    @Override
    public boolean isFriend(final Piece target) {
        return isColorMatch(target.getColor());
    }

    @Override
    public boolean isEnemy(final Piece target) {
        return color.isEnemyColor(target.getColor());
    }

    @Override
    public boolean isColorMatch(final Color color) {
        return this.color == color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
