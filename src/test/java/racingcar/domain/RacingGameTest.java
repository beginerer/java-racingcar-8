package racingcar.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class RacingGameTest {

    private static final int round = 5;

    private static final int DEFAULT_ROUND = 0;

    private static final String[] input = {"A","B","C"};

    private static final String[] initialState = {"A : ", "B : ", "C : "};


    @Test
    @DisplayName("Racing Game 객체 생성 테스트")
    public void test() {
        RacingGame game = new RacingGame(input, round);

        Assertions.assertThat(game.isFinished()).isFalse();
        Assertions.assertThat(game.getRoundState(DEFAULT_ROUND)).isEqualTo(game.getCurrentRoundState());
        Assertions.assertThat(game.getRoundState(DEFAULT_ROUND)).isEqualTo(initialState);
        Assertions.assertThat(game.getRound()).isEqualTo(round);
        Assertions.assertThat(game.getCurrentRound()).isEqualTo(DEFAULT_ROUND);
        Assertions.assertThat(game.getParticipants()).isEqualTo(input);
        Assertions.assertThat(game.getState()).isEqualTo(GameState.READY);
    }

    @Test
    @DisplayName("경주가 완료되기 전에 우승자를 조회하면 예외가 발생한다.")
    public void test2() {
        RacingGame game = new RacingGame(input, round);

        Assertions.assertThatThrownBy(game::getWinnerNames).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 경주가 아직 진행중에 있습니다.");
    }

    @Test
    @DisplayName("한 라운드 진행이 완료될 경우 currentRound가 1 증가한다.")
    public void test3() {
        //given
        RacingGame game = new RacingGame(input, round);
        //when
        game.playRound();
        //then
        Assertions.assertThat(game.getCurrentRound()).isEqualTo(DEFAULT_ROUND + 1);
        Assertions.assertThat(game.getState()).isEqualTo(GameState.RUNNING);
        Assertions.assertThat(game.isFinished()).isFalse();
    }

    @Test
    @DisplayName("모든 라운드가 진행되면 경기는 종료된다.(모든 라운드 한번에 진행)")
    public void test4() {
        //given
        RacingGame game = new RacingGame(input, round);
        //when
        game.playAllRounds();
        //then
        Assertions.assertThat(game.isFinished()).isTrue();
        Assertions.assertThat(game.getState()).isEqualTo(GameState.FINISHED);
        Assertions.assertThat(game.getCurrentRound()).isEqualTo(round);
        Assertions.assertThat(game.getWinnerNames()).isNotNull();
    }

    @Test
    @DisplayName("모든 라운드가 진행되면 경기는 종료된다.(한 라운드씩 진행)")
    public void test5() {
        //given
        RacingGame game = new RacingGame(input, round);
        //when
        while(!game.isFinished()) {
            game.playRound();
        }
        //then
        Assertions.assertThat(game.isFinished()).isTrue();
        Assertions.assertThat(game.getState()).isEqualTo(GameState.FINISHED);
        Assertions.assertThat(game.getCurrentRound()).isEqualTo(round);
        Assertions.assertThat(game.getWinnerNames()).isNotNull();
    }

    @Test
    @DisplayName("경기가 종료된 상태에서 reset메서드를 호출하면 다시 게임을 진행할 수 있다.")
    public void test6() {
        //given
        RacingGame game = new RacingGame(input, round);
        game.playAllRounds();

        Assertions.assertThat(game.isFinished()).isTrue();
        Assertions.assertThat(game.getState()).isEqualTo(GameState.FINISHED);
        Assertions.assertThat(game.getCurrentRound()).isEqualTo(round);
        Assertions.assertThat(game.getWinnerNames()).isNotNull();

        //when
        game.reset();

        //then
        Assertions.assertThat(game.isFinished()).isFalse();
        Assertions.assertThat(game.getRoundState(DEFAULT_ROUND)).isEqualTo(game.getCurrentRoundState());
        Assertions.assertThat(game.getRoundState(DEFAULT_ROUND)).isEqualTo(initialState);
        Assertions.assertThat(game.getRound()).isEqualTo(round);
        Assertions.assertThat(game.getCurrentRound()).isEqualTo(DEFAULT_ROUND);
        Assertions.assertThat(game.getParticipants()).isEqualTo(input);
        Assertions.assertThat(game.getState()).isEqualTo(GameState.READY);
    }
}