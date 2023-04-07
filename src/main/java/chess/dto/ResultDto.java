package chess.dto;

import chess.chessboard.Side;

public class ResultDto {

    private final Side winner;

    public ResultDto(final Side winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner.name();
    }
}
