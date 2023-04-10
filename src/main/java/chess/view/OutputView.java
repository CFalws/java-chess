package chess.view;

import chess.dto.PieceDto;
import chess.dto.ScoreDto;
import chess.dto.WinnerDto;

public class OutputView {

    public static final String BLACK_COLOR = "BLACK";
    public static final String WHITE_COLOR = "WHITE";

    private static final String PROMPT = "> ";

    public void printInstructionMessage() {
        printMessageStartingWithPrompt("체스 게임을 시작합니다.");
        printMessageStartingWithPrompt("게임 불러오기 : load");
        printMessageStartingWithPrompt("새로운 게임 시작 : start");
        printMessageStartingWithPrompt("게임 종료 : end");
        printMessageStartingWithPrompt("게임 이동 : move from위치 target위치 - 예. move b2 b3");
        printMessageStartingWithPrompt("점수 확인 : status");
    }

    private void printMessageStartingWithPrompt(final String message) {
        println(PROMPT + message);
    }

    public void printNewGameStartingMessage() {
        println("새로운 게임을 시작합니다");
    }

    public void printChessBoard(PieceDto[][] chessBoardDto) {
        println("");
        for (final PieceDto[] rank : chessBoardDto) {
            printPiecesOfRank(rank);
        }
    }

    private void printPiecesOfRank(final PieceDto[] rank) {
        for (final PieceDto pieceDto : rank) {
            printPiece(pieceDto);
        }
        println("");
    }

    private void printPiece(final PieceDto pieceDto) {
        print(generatePieceOutput(pieceDto));
    }

    private String generatePieceOutput(final PieceDto pieceDto) {
        final String color = pieceDto.getColor();
        final String type = toOutputFormat(pieceDto.getType());

        if (color.equals(BLACK_COLOR)) {
            return type.toUpperCase();
        }
        if (color.equals(WHITE_COLOR)) {
            return type.toLowerCase();
        }
        return type;
    }

    private String toOutputFormat(final String type) {
        switch (type) {
            case "PAWN": {
                return "p";
            }
            case "BISHOP": {
                return "b";
            }
            case "ROOK": {
                return "r";
            }
            case "KNIGHT": {
                return "n";
            }
            case "QUEEN": {
                return "q";
            }
            case "KING": {
                return "k";
            }
            case "EMPTY": {
                return ".";
            }
        }
        throw new IllegalArgumentException("존재하지 않는 기물입니다");
    }

    public void printScore(final ScoreDto scoreDto) {
        println("백팀: " + scoreDto.getWhiteScore());
        println("흑팀: " + scoreDto.getBlackScore());
    }

    public void printWinner(final WinnerDto winnerDto) {
        final String winner = winnerDto.getWinner();

        println(winner + "가 왕을 잡았습니다!");
        println("승자: " + winner);
    }

    public void printGameEndMessage() {
        println("게임을 종료합니다!");
    }

    private void print(final String message) {
        System.out.print(message);
    }

    private void println(final String message) {
        System.out.println(message);
    }
}
