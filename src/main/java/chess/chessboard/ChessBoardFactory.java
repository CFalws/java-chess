package chess.chessboard;

import chess.piece.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardFactory {

    public ChessBoard generate() {
        return new ChessBoard(makePieces());
    }

    private Map<Square, Piece> makePieces() {
        final HashMap<Square, Piece> pieces = new HashMap<>();
        addRooks(pieces);
        addKnights(pieces);
        addBishops(pieces);
        addQueens(pieces);
        addKings(pieces);
        addPawns(pieces);
        addEmptyPieces(pieces);
        return pieces;
    }

    private void addRooks(final HashMap<Square, Piece> pieces) {
        final List<Rook> blackRooks = Rook.getRooksOf(Side.BLACK);
        final List<Rook> whiteRooks = Rook.getRooksOf(Side.WHITE);
        pieces.put(Square.of(Rank.EIGHT, File.A), blackRooks.get(0));
        pieces.put(Square.of(Rank.EIGHT, File.H), blackRooks.get(1));
        pieces.put(Square.of(Rank.ONE, File.A), whiteRooks.get(0));
        pieces.put(Square.of(Rank.ONE, File.H), whiteRooks.get(1));
    }

    private void addKnights(final HashMap<Square, Piece> pieces) {
        final List<Knight> blackKnights = Knight.getKnightsOf(Side.BLACK);
        final List<Knight> whiteKnights = Knight.getKnightsOf(Side.WHITE);
        pieces.put(Square.of(Rank.EIGHT, File.B), blackKnights.get(0));
        pieces.put(Square.of(Rank.EIGHT, File.G), blackKnights.get(1));
        pieces.put(Square.of(Rank.ONE, File.B), whiteKnights.get(0));
        pieces.put(Square.of(Rank.ONE, File.G), whiteKnights.get(1));
    }

    private void addBishops(final HashMap<Square, Piece> pieces) {
        final List<Bishop> blackBishops = Bishop.getBishopsOf(Side.BLACK);
        final List<Bishop> whiteBishops = Bishop.getBishopsOf(Side.WHITE);
        pieces.put(Square.of(Rank.EIGHT, File.C), blackBishops.get(0));
        pieces.put(Square.of(Rank.EIGHT, File.F), blackBishops.get(1));
        pieces.put(Square.of(Rank.ONE, File.C), whiteBishops.get(0));
        pieces.put(Square.of(Rank.ONE, File.F), whiteBishops.get(1));
    }

    private void addQueens(final HashMap<Square, Piece> pieces) {
        pieces.put(Square.of(Rank.EIGHT, File.D), Queen.getQueenOf(Side.BLACK));
        pieces.put(Square.of(Rank.ONE, File.D), Queen.getQueenOf(Side.WHITE));
    }

    private void addKings(final HashMap<Square, Piece> pieces) {
        pieces.put(Square.of(Rank.EIGHT, File.E), King.getKingOf(Side.BLACK));
        pieces.put(Square.of(Rank.ONE, File.E), King.getKingOf(Side.WHITE));
    }

    private void addPawns(final HashMap<Square, Piece> pieces) {
        final List<Pawn> blackPawns = Pawn.getPawnsOf(Side.BLACK);
        final List<Pawn> whitePawns = Pawn.getPawnsOf(Side.WHITE);
        for (File file : File.values()) {
            pieces.put(Square.of(Rank.SEVEN, file), blackPawns.get(file.ordinal()));
            pieces.put(Square.of(Rank.TWO, file), whitePawns.get(file.ordinal()));
        }
    }

    private void addEmptyPieces(final HashMap<Square, Piece> pieces) {
        for (File file : File.values()) {
            pieces.put(Square.of(Rank.THREE, file), EmptyPiece.getInstance());
            pieces.put(Square.of(Rank.FOUR, file), EmptyPiece.getInstance());
            pieces.put(Square.of(Rank.FIVE, file), EmptyPiece.getInstance());
            pieces.put(Square.of(Rank.SIX, file), EmptyPiece.getInstance());
        }
    }
}