package racingcar.dto;

public class GameRequest {

    private final String[] carNames;

    private final int round;


    public GameRequest(String[] carNames, int round) {
        this.carNames = carNames;
        this.round = round;
    }


    public String[] getCarNames() {
        return carNames;
    }

    public int getRound() {
        return round;
    }
}
