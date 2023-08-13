package chess.dto;

public class ChessGameDto {

    private final int id;
    private final String turn;
    private final String gameStatus;

    public ChessGameDto(final int id, final String turn, final String gameStatus) {
        this.id = id;
        this.turn = turn;
        this.gameStatus = gameStatus;
    }

    public int getId() {
        return id;
    }

    public String getTurn() {
        return turn;
    }

    public String getGameStatus() {
        return gameStatus;
    }
}
