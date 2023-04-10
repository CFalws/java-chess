package chess.dao;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessgame.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.ChessGameDto;
import chess.dto.PieceDto;
import chess.dto.dtomapper.ChessGameMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessGameDaoImpl implements ChessGameDao {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public void save(final ChessGame chessGame) {
        saveGame(chessGame);
        savePieces(chessGame);
    }

    private void saveGame(final ChessGame chessGame) {
        final var chessGameStatement = "INSERT INTO chess_game(id, turn, play_state) VALUES(?, ?, ?)";
        final ChessGameDto dto = ChessGameMapper.toDto(chessGame);
        jdbcTemplate.update(chessGameStatement, dto.getId(), dto.getTurn(), dto.getGameStatus());
    }

    private void savePieces(final ChessGame chessGame) {
        final var piecesStatement = "INSERT INTO pieces(chess_game_id, color, type, `file`, `rank`) VALUES(?, ?, ?, ?, ?)";
        final Map<Position, Piece> pieces = chessGame.getChessBoard()
                                                     .getPieces();
        for (final Piece piece : pieces.values()) {
            final PieceDto pieceDto = RowMapper.toPieceDto(piece);
            jdbcTemplate.update(piecesStatement, chessGame.getId(), pieceDto.getColor(), pieceDto.getType(), pieceDto.getFile(), pieceDto.getRank());
        }
    }

    @Override
    public void deleteGameById(int id) {
        final String piecesDeleteStmt = "DELETE FROM pieces where chess_game_id = ?";
        final String chessGameDeleteStmt = "DELETE FROM chess_game where id = ?";

        jdbcTemplate.update(piecesDeleteStmt, id);
        jdbcTemplate.update(chessGameDeleteStmt, id);
    }

    @Override
    public void update(final ChessGame chessGame) {
        deleteGameById(chessGame.getId());
        save(chessGame);
    }

    @Override
    public Optional<ChessGame> findById(final int gameId) {
        final var piecesQuery = "SELECT color, type, `file`, `rank` from pieces WHERE chess_game_id = ?";
        final var chessGameQuery = "SELECT id, turn, play_state from chess_game WHERE id = ?";
        final ChessBoard chessBoard = RowMapper.toChessBoard(jdbcTemplate.query(piecesQuery, gameId));
        final ChessGame chessGame = findChessGame(chessGameQuery, gameId, chessBoard);
        return Optional.ofNullable(chessGame);
    }

    private ChessGame findChessGame(final String chessGameQuery, final int gameId, final ChessBoard chessBoard) {
        final List<List<String>> gameData = jdbcTemplate.query(chessGameQuery, gameId);
        if (gameData.isEmpty()) {
            return null;
        }
        validateGameDataSizeIsOne(gameData);
        return RowMapper.toChessGame(gameData.get(0), chessBoard);
    }

    private void validateGameDataSizeIsOne(final List<List<String>> gameData) {
        if (gameData.size() != 1) {
            throw new IllegalArgumentException("이상하다");
        }
    }
}
