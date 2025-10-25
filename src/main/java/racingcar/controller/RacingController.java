package racingcar.controller;

import racingcar.domain.RacingGame;
import racingcar.dto.GameRequest;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Arrays;


public class RacingController {


    public RacingController() {
    }

    /**
     * 한번에 모든 라운드 실행하는 방식*/
    public void run() {

        String[] rawCarNames = InputView.inputCarNames();
        int round = InputView.inputRound();

        String[] carNames = removeSpace(rawCarNames);

        GameRequest request = new GameRequest(carNames, round);

        RacingGame game = new RacingGame(request);

        game.playAllRounds();

        OutputView.showGameResult(game);
    }


    /**
     * 한 라운드씩 실행하는 방식*/
    public void run2() {
        String[] rawCarNames = InputView.inputCarNames();
        int round = InputView.inputRound();

        String[] carNames = removeSpace(rawCarNames);

        GameRequest request = new GameRequest(carNames, round);

        RacingGame game = new RacingGame(request);


        OutputView.printStartMessage();
        while (!game.isFinished()) {
            game.playRound();
            OutputView.showCurrentRoundState(game);
        }
        OutputView.showWinners(game);
    }

    private String[] removeSpace(String[] rawCarNames) {
        return Arrays.stream(rawCarNames).map(String::trim).toArray(String[]::new);
    }
}
