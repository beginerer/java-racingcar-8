package racingcar.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RacingGameTest {

    private static final int round = 5;



    @Test
    @DisplayName("중복된 이름이 존재할 경우 접미사를 추가해준다.")
    public void test() {
        String[] input = {"홍길동","홍길동","홍길동"};
        RacingGame game = new RacingGame(input, round);

        String[] participants = game.getParticipants();

        Assertions.assertThat(findParticipants(participants,"홍길동")).isTrue();
        Assertions.assertThat(findParticipants(participants,"홍길동1")).isTrue();
        Assertions.assertThat(findParticipants(participants,"홍길동2")).isTrue();
    }

    @Test
    @DisplayName("자동차 이름이 5글자를 초과한다면 예외가 발생한다.")
    public void test2() {
        String[] input = {"가나다라마사","여섯글자입니"};

        Assertions.assertThatThrownBy(() -> new RacingGame(input, round))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 자동차 이름은 5글자 이하만 가능합니다.");
    }

    @Test
    @DisplayName("시도 횟수가 음수라면 예외가 발생한다.")
    public void test3() {
        String[] input = {"홍길동","홍길동","홍길동"};

        Assertions.assertThatThrownBy(() -> new RacingGame(input, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 라운드는 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("자동차 이름에 공백이 입력된다면 예외가 발생한다.")
    public void test4() {
        String[] input = {"","홍길동","홍길동"};

        Assertions.assertThatThrownBy(() -> new RacingGame(input, round))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 자동차 이름이 비어 있습니다.");
    }


    private boolean findParticipants(String[] participants, String name) {
        for(String participant : participants) {
            if(participant.equals(name))
                return true;
        }
        return false;
    }

}