package chess.service;

import chess.chessboard.ChessBoard;
import chess.chessboard.ChessBoardFactory;
import chess.chessboard.Position;
import chess.chessboard.Side;
import chess.chessgame.ChessGame;
import chess.chessgame.PlayerScore;
import chess.controller.ChessBoardDto;
import chess.controller.MoveCommand;
import chess.controller.ScoreDto;
import chess.dao.ChessGameDao;
import chess.dao.ChessGameDaoImpl;
import chess.dto.ResultDto;

import java.util.Optional;

public class ChessGameService {

    private final ChessGameDao chessGameDao = new ChessGameDaoImpl();

    public ChessGame readGame() {
        final Optional<ChessGame> optionalChessGame = chessGameDao.find();

        return optionalChessGame.orElseGet(this::generateNewChessGame);
    }

    private ChessGame generateNewChessGame() {
        final ChessBoardFactory chessBoardFactory = new ChessBoardFactory();
        final ChessBoard chessBoard = chessBoardFactory.generate();
        final ChessGame chessGame = new ChessGame(chessBoard);

        chessGameDao.save(chessGame);

        return chessGame;
    }

    public boolean isGameOver(final ChessGame chessGame) {
        return chessGame.isGameOver();
    }

    public ResultDto getResult(final ChessGame chessGame) {
        return new ResultDto(chessGame.getWinner());
    }

    public ScoreDto calculateScores(final ChessGame chessGame) {
        final PlayerScore whiteScore = chessGame.calculateScore(Side.WHITE);
        final PlayerScore blackScore = chessGame.calculateScore(Side.BLACK);

        return new ScoreDto(whiteScore, blackScore);
    }

    public ChessBoardDto moveWithCapture(final ChessGame chessGame, final MoveCommand moveCommand) {
        final Position from = moveCommand.getSourcePosition();
        final Position to = moveCommand.getDestinationPosition();

        chessGame.moveWithCapture(from, to);

        return ChessBoardDto.from(chessGame.getChessBoard());
    }

    public ChessBoardDto getChessBoard(final ChessGame chessGame) {
        return ChessBoardDto.from(chessGame.getChessBoard());
    }
}
