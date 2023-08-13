package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.*;

public final class PieceFactory {

    private PieceFactory() {
    }

    public static Piece createPiece(final Color pieceColor, final Position position, final PieceType pieceType) {
        if (pieceType == PieceType.PAWN) {
            return new Pawn(pieceColor, position);
        }
        if (pieceType == PieceType.BISHOP) {
            return new NonPawnPiece(pieceColor, position, BishopMove.getInstance());
        }
        if (pieceType == PieceType.ROOK) {
            return new NonPawnPiece(pieceColor, position, RookMove.getInstance());
        }
        if (pieceType == PieceType.KNIGHT) {
            return new NonPawnPiece(pieceColor, position, KnightMove.getInstance());
        }
        if (pieceType == PieceType.QUEEN) {
            return new NonPawnPiece(pieceColor, position, QueenMove.getInstance());
        }
        if (pieceType == PieceType.KING) {
            return new NonPawnPiece(pieceColor, position, KingMove.getInstance());
        }
        return new Empty(position);
    }
}
