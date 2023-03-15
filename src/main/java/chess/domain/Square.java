package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Square {

    private static final Map<Integer, Square> cache = new HashMap<>();

    private final Rank rank;
    private final File file;

    private Square(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Square of(final Rank rank, final File file) {
        return cache.computeIfAbsent(toKey(rank, file), key -> new Square(rank, file));
    }

    private static Integer toKey(final Rank rank, final File file) {
        return Objects.hash(rank, file);
    }


    public boolean inLine(final Square to) {
        checkSameSquare(to);
        return this.rank == to.rank || this.file == to.file;
    }

    public boolean inDiagonal(final Square to) {
        checkSameSquare(to);
        final int verticalDistance = this.rank.distanceTo(to.rank);
        final int horizontalDistance = this.file.distanceTo(to.file);
        return verticalDistance == horizontalDistance;
    }

    public boolean inLShape(final Square to) {
        checkSameSquare(to);
        final int verticalDistance = this.rank.distanceTo(to.rank);
        final int horizontalDistance = this.file.distanceTo(to.file);
        if (verticalDistance == 0 || horizontalDistance == 0) {
            return false;
        }
        return verticalDistance + horizontalDistance == 3;
    }

    public boolean inPawnsInitialMovableRange(final Square to, final Side side) {
        checkSameSquare(to);
        final int verticalDistance = rank.distanceTo(to.rank);
        return isBackOf(to, side) &&
                (verticalDistance == 1 || verticalDistance == 2) &&
                file.distanceTo(to.file) == 0;
    }

    public boolean inPawnsMovableRange(final Square to, final Side side) {
        checkSameSquare(to);
        final int verticalDistance = rank.distanceTo(to.rank);
        return isBackOf(to, side) &&
                verticalDistance == 1 &&
                file.distanceTo(to.file) == 0;
    }

    public boolean inPawnsCatchableRange(final Square to, final Side side) {
        checkSameSquare(to);
        final int verticalDistance = rank.distanceTo(to.rank);
        return isBackOf(to, side) &&
                verticalDistance == 1 &&
                file.distanceTo(to.file) == 1;
    }

    private boolean isBackOf(final Square to, final Side side) {
        if (side == Side.WHITE) {
            return to.rank.isBiggerThan(this.rank);
        }
        return this.rank.isBiggerThan(to.rank);
    }

    private void checkSameSquare(final Square to) {
        if (this == to) {
            throw new IllegalArgumentException("같은 지점이 들어왔습니다");
        }
    }

    public int getRank() {
        return rank.getPosition();
    }

    public File getFile() {
        return file;
    }
}
