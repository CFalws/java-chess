package chess.chessgame;

import chess.chessboard.ChessBoard;
import chess.chessboard.Position;
import chess.chessboard.Side;
import chess.piece.Piece;
import chess.status.GameStatus;
import chess.status.KingAliveStatus;
import chess.status.ReadyStatus;

import java.util.Map;

public class ChessGame {

    private static final ChessGame unplayableGame = new ChessGame(null, new ReadyStatus());

    private final ChessBoard chessBoard;
    private GameStatus gameStatus;

    public ChessGame(final ChessBoard chessBoard) {
        this(chessBoard, new KingAliveStatus(Side.initialTurn()));
    }

    public ChessGame(final ChessBoard chessBoard, final GameStatus gameStatus) {
        this.chessBoard = chessBoard;
        this.gameStatus = gameStatus;
    }

    public static ChessGame getUnplayableGame() {
        return unplayableGame;
    }

    public void moveWithCapture(final Position from, final Position to) {
        gameStatus.validateMove(chessBoard, from, to);

        final Piece capturedPiece = chessBoard.moveWithCapture(from, to);

        gameStatus = gameStatus.nextStatus(chessBoard);
    }

    public boolean isGameOver() {
        return gameStatus.isGameOver();
    }

    public PlayerScore calculateScore(Side player) {
        final Map<Position, Piece> piecesOfPlayer = chessBoard.getPieces(player);

        return PlayerScore.from(piecesOfPlayer);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Side getWinner() {
        return gameStatus.getWinner();
    }

    public Side getTurn() {
        return gameStatus.getTurn();
    }
}
