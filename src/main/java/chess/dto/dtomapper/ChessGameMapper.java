package chess.dto.dtomapper;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessgame.ChessGame;
import chess.domain.piece.Color;
import chess.domain.status.KingAliveStatus;
import chess.domain.status.KingDeadStatus;
import chess.dto.ChessGameDto;

public final class ChessGameMapper {

    private ChessGameMapper() {
    }

    public static ChessGameDto toDto(ChessGame chessGame) {
        return new ChessGameDto(chessGame.getId(), getTurn(chessGame.getTurn()), toGameState(chessGame.isGameOver()));
    }

    private static String getTurn(final Color turn) {
        if (turn == Color.WHITE) {
            return "WHITE";
        }
        return "BLACK";
    }

    private static String toGameState(final boolean gameOver) {
        if (gameOver) {
            return "KingDead";
        }
        return "KingAlive";
    }

    public static ChessGame toChessGame(final String id, final String turn, final String gameStatus, final ChessBoard chessBoard) {
        if ("KingAlive".equals(gameStatus)) {
            return ChessGame.createChessGame(Integer.parseInt(id), chessBoard, new KingAliveStatus(PieceMapper.toColor(turn)));
        }
        return ChessGame.createChessGame(Integer.parseInt(id), chessBoard, new KingDeadStatus(PieceMapper.toColor(turn)));
    }
}
