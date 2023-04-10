package chess.status;

import chess.chessboard.ChessBoard;
import chess.chessboard.Position;
import chess.chessboard.Side;

public class ReadyStatus implements GameStatus {
    @Override
    public void validateMove(final ChessBoard chessBoard, final Position from, final Position to) {
        throw new IllegalArgumentException("게임이 진행 중이지 않습니다");
    }

    @Override
    public GameStatus nextStatus(final ChessBoard chessBoard) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isGameOver() {
        return true;
    }

    @Override
    public Side getWinner() {
        throw new IllegalArgumentException("");
    }

    @Override
    public Side getTurn() {
        throw new UnsupportedOperationException();
    }
}
