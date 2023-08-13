package chess.dao;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessgame.ChessGame;
import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import chess.dto.dtomapper.ChessGameMapper;
import chess.dto.dtomapper.PieceMapper;

import java.util.ArrayList;
import java.util.List;

public final class RowMapper {

    private RowMapper() {
    }

    public static ChessBoard toChessBoard(final List<List<String>> piecesData) {
        List<Piece> pieces = new ArrayList<>();
        for (final List<String> pieceData : piecesData) {
            pieces.add(toPiece(pieceData.get(1), pieceData.get(0), pieceData.get(2), pieceData.get(3)));
        }
        return ChessBoard.of(pieces);
    }

    public static ChessGame toChessGame(final List<String> gameData, final ChessBoard chessBoard) {
        return ChessGameMapper.toChessGame(gameData.get(0), gameData.get(1), gameData.get(2), chessBoard);
    }

    public static Piece toPiece(final String type, final String color, final String file, final String rank) {
        return PieceMapper.toPiece(type, color, file, rank);
    }

    public static PieceDto toPieceDto(Piece piece) {
        return PieceMapper.toDto(piece);
    }
}
