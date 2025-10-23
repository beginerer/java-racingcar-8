package racingcar.view;

import racingcar.domain.RacingGame;

public class OutputView {

    private static final String DELIMITER = ", ";




    public static void showCurrentRoundState(RacingGame game) {
        String[] currentRoundState = game.getCurrentRoundState();

        String roundState = buildRoundState(currentRoundState);
        System.out.println(roundState);
    }


    public static void showGameResult(RacingGame game) {
        if(!game.isFinished())
            throw new IllegalStateException("[ERROR] 경주가 아직 진행중에 있습니다. currentRound=%d, round=%d".
                    formatted(game.getCurrentRound(), game.getRound()));

        System.out.println("실행 결과");

        for(int i=1; i<=game.getRound(); i++) {
            String[] carRoundStates = game.getRoundState(i);
            String roundState = buildRoundState(carRoundStates);
            System.out.println(roundState);
        }
        showWinners(game);
    }

    public static void showWinners(RacingGame game) {
        if(!game.isFinished())
            throw new IllegalStateException("[ERROR] 경주가 아직 진행중에 있습니다. currentRound=%d, round=%d".
                    formatted(game.getCurrentRound(), game.getRound()));

        String winners = String.join(DELIMITER, game.getWinners());
        System.out.println("최종 우승자 : " + winners);
    }

    public static void printStartMessage() {
        System.out.println("실행 결과");
    }



    private static String buildRoundState(String[] roundState) {
        if(roundState == null || roundState.length == 0)
            return "";

        StringBuilder sb = new StringBuilder();

        for(String state : roundState) {
            sb.append(state).append("\n");
        }
        return sb.toString();
    }
}
