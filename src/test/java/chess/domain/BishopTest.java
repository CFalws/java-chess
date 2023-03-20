package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Bishop 클래스")
public class BishopTest {

    @Nested
    @DisplayName("of 메서드는")
    class of {
        @Nested
        @DisplayName("진영이 주어지면")
        class given_team {
            @Test
            @DisplayName("해당 진영의 Bishop을 2개 생성한다")
            void it_returns_bishops() {
                assertThat(Bishop.of(Side.BLACK)).hasSize(2);
            }
        }
    }

    @Nested
    @DisplayName("isMovable 메서드는")
    class isMovable {
        Bishop whiteBishop = Bishop.of(Side.WHITE)
                                   .get(0);
        Square fromSquare = Square.of(Rank.FOUR, File.D);
        Square movableSquare1 = Square.of(Rank.ONE, File.G);
        Square movableSquare2 = Square.of(Rank.SIX, File.B);
        Square unMovableSquare = Square.of(Rank.THREE, File.B);
        Bishop whiteBishop2 = Bishop.of(Side.WHITE)
                                    .get(1);
        Queen blackQueen = Queen.of(Side.BLACK);

        @Nested
        @DisplayName("이동 가능한 위치에 ")
        class context1 {
            @Nested
            @DisplayName("아군 기물이 있지 않다면")
            class context2 {
                @Test
                @DisplayName("true를 반환한다")
                void it_returns_true() {
                    assertAll(
                            () -> assertThat(whiteBishop.isMovable(fromSquare, movableSquare1, blackQueen)).isTrue(),
                            () -> assertThat(whiteBishop.isMovable(fromSquare, movableSquare2, blackQueen)).isTrue(),
                            () -> assertThat(whiteBishop.isMovable(fromSquare, movableSquare1, EmptyPiece.getInstance())).isTrue()
                    );
                }
            }

            @Nested
            @DisplayName("아군 기물이 있다면")
            class context3 {
                @Test
                @DisplayName("false를 반환한다")
                void it_returns_false() {
                    assertThat(whiteBishop.isMovable(fromSquare, movableSquare1, whiteBishop2)).isFalse();
                }
            }
        }

        @Nested
        @DisplayName("갈 수 없는 위치라면")
        class context4 {
            @Test
            @DisplayName("false를 반환한다")
            void it_returns_false() {
                assertThat(whiteBishop.isMovable(fromSquare, unMovableSquare, blackQueen)).isFalse();
            }
        }
    }
}
