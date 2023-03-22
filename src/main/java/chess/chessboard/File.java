package chess.chessboard;

import java.util.*;
import java.util.stream.Collectors;

public enum File {
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private final int position;
    private final String symbol;

    File(final int position, final String symbol) {
        this.position = position;
        this.symbol = symbol;
    }

    public static File from(final String symbol) {
        return Arrays.stream(values())
                     .filter(file -> Objects.equals(symbol, file.symbol))
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일입니다"));
    }

    public static List<File> filesBetween(final File source, final File destination) {
        final int smaller = Math.min(source.position, destination.position);
        final int bigger = Math.max(source.position, destination.position);
        List<File> files = Arrays.stream(values())
                                 .filter(file -> file.position > smaller && file.position < bigger)
                                 .collect(Collectors.toList());

        return sortByPosition(source, destination, files);
    }

    private static List<File> sortByPosition(final File source, final File destination, final List<File> files) {
        files.sort(Comparator.comparing(File::getPosition));
        if (source.position > destination.position) {
            Collections.reverse(files);
        }
        return files;
    }

    public int distanceTo(final File other) {
        return Math.abs(position - other.position);
    }

    public int getPosition() {
        return position;
    }
}