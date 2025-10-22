package racingcar.view;

import racingcar.domain.RacingGame;

public class OutputView {




    public static void showRoundState(RacingGame game) {
        String[] currentRoundState = game.getCurrentRoundState();

        StringBuilder sb = new StringBuilder();

        for(String state : currentRoundState) {
            sb.append(state).append("\n");
        }

        System.out.println(sb.toString());
    }
}
