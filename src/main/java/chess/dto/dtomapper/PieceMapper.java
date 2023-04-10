package chess.dto.dtomapper;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.strategy.piecemovestrategy.PieceType;
import chess.dto.PieceDto;

public class PieceMapper {

    private PieceMapper() {
    }

    public static PieceDto toDto(Piece piece) {
        final PieceType pieceType = piece.getPieceType();
        final Color pieceColor = piece.getColor();
        final Position position = piece.getPosition();
        final Rank rank = position.getRank();
        final File file = position.getFile();
        String type = toString(pieceType);
        String color = toString(pieceColor);
        return new PieceDto(type, color, file.getSymbol(), rank.getSymbol());
    }

    private static String toString(final PieceType pieceType) {
        switch (pieceType) {
            case PAWN: {
                return "PAWN";
            }
            case BISHOP: {
                return "BISHOP";
            }
            case ROOK: {
                return "ROOK";
            }
            case KNIGHT: {
                return "KNIGHT";
            }
            case QUEEN: {
                return "QUEEN";
            }
            case KING: {
                return "KING";
            }
            case EMPTY: {
                return "EMPTY";
            }
        }
        throw new IllegalArgumentException("뭔가 단단히 문제가 있습니다");
    }

    private static String toString(final Color pieceColor) {
        if (pieceColor == Color.WHITE) {
            return "WHITE";
        }
        if (pieceColor == Color.BLACK) {
            return "BLACK";
        }
        return "EMPTY";
    }

    public static Piece toPiece(final PieceDto pieceDto) {
        return toPiece(pieceDto.getType(), pieceDto.getColor(), pieceDto.getFile(), pieceDto.getRank());
    }

    public static Piece toPiece(final String type, final String color, final String file, final String rank) {
        final PieceType pieceType = toPieceType(type);
        final Color pieceColor = toColor(color);
        final Position position = Position.of(Rank.from(rank), File.from(file));

        return PieceFactory.createPiece(pieceColor, position, pieceType);
    }

    private static PieceType toPieceType(final String type) {
        switch (type) {
            case "PAWN": {
                return PieceType.PAWN;
            }
            case "BISHOP": {
                return PieceType.BISHOP;
            }
            case "ROOK": {
                return PieceType.ROOK;
            }
            case "KNIGHT": {
                return PieceType.KNIGHT;
            }
            case "QUEEN": {
                return PieceType.QUEEN;
            }
            case "KING": {
                return PieceType.KING;
            }
            case "EMPTY": {
                return PieceType.EMPTY;
            }
        }
        throw new IllegalArgumentException("뭔가 단단히 문제가 있습니다");
    }

    static Color toColor(final String color) {
        switch (color) {
            case "WHITE": {
                return Color.WHITE;
            }
            case "BLACK": {
                return Color.BLACK;
            }
            case "EMPTY": {
                return Color.EMPTY;
            }
        }
        throw new IllegalArgumentException("문제");
    }
}
