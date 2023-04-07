package chess.chessgame;

import chess.chessboard.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {
    @Nested
    @DisplayName("move 메서드는")
    class move {
        ChessGame chessGame;

        @BeforeEach
        void initializeBoard() {
            final ChessBoard chessBoard = new ChessBoardFactory().generate();
            chessGame = new ChessGame(chessBoard);
        }

        @Nested
        @DisplayName("이동 순서가 있는데")
        class sequence {
            Position whiteSource1 = Position.of(Rank.TWO, File.B);
            Position blackSource = Position.of(Rank.SEVEN, File.C);
            Position whiteDestination1 = Position.of(Rank.FOUR, File.B);
            Position blackDestination = Position.of(Rank.FIVE, File.C);
            Position whiteSource2 = Position.of(Rank.FOUR, File.B);
            Position whiteDestination2 = Position.of(Rank.FIVE, File.C);


            @Test
            @DisplayName("처음에 백이 먼저 공격해야 정상적으로 동작한다")
            void it_returns_empty_piece1() {
                assertThatNoException().isThrownBy(() -> chessGame.moveWithCapture(whiteSource1, whiteDestination1));
            }

            @Test
            @DisplayName("처음에 흑이 먼저 공격하면 예외를 던진다")
            void it_throws_exception1() {
                assertThatThrownBy(() -> chessGame.moveWithCapture(blackSource, blackDestination))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("공격 순서가 잘못되었습니다");
            }

            @Test
            @DisplayName("백이 공격하고 나서 흑이 공격해야 정상동작한다")
            void it_returns_empty_piece2() {
                chessGame.moveWithCapture(whiteSource1, whiteDestination1);

                assertThatNoException().isThrownBy(() -> chessGame.moveWithCapture(blackSource, blackDestination));
            }

            @Test
            @DisplayName("흑이 공격하고 나서 백이 공격해야 정상동작한다")
            void it_returns_pawn1() {
                chessGame.moveWithCapture(whiteSource1, whiteDestination1);
                chessGame.moveWithCapture(blackSource, blackDestination);

                assertThatNoException().isThrownBy(() -> chessGame.moveWithCapture(whiteSource2, whiteDestination2));
            }

            @Test
            @DisplayName("백이 공격하고 나서 백이 공격하면 예외를 던진다")
            void it_throws_exception2() {
                chessGame.moveWithCapture(whiteSource1, whiteDestination1);

                assertThatThrownBy(() -> chessGame.moveWithCapture(whiteSource2, whiteDestination2))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("공격 순서가 잘못되었습니다");
            }
        }
    }
}
