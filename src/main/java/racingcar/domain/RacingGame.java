package racingcar.domain;


import racingcar.dto.GameRequest;


public class RacingGame {

    private static final int DEFAULT_ROUND = 0;


    private final RacingCars racingCars;

    private int currentRound;

    private final int round;

    private final RoundSnapShot snapShot;

    private GameState state;



    public RacingGame(GameRequest request) {
        this(request.getCarNames(), request.getRound());
    }


    public RacingGame(String[] carNames, int round) {
        this.racingCars = new RacingCars(carNames);
        this.round = validateRound(round);
        this.currentRound = DEFAULT_ROUND;
        this.snapShot = initializeRoundSnapShot();
        this.state = GameState.READY;
    }



    public void playAllRounds() {
        if(state == GameState.FINISHED || currentRound >= round)
            throw new IllegalStateException("[ERROR] 이미 종료된 게임입니다.");

        this.state = GameState.RUNNING;

        for (int i=currentRound; i<round; i++) {
            racingCars.move();

            currentRound++;
            snapShot.recordRoundState(getCurrentRoundState(), currentRound);
        }
        this.state = GameState.FINISHED;
    }

    public void playRound() {
        if(state == GameState.FINISHED || currentRound >= round)
            throw new IllegalStateException("[ERROR] 이미 종료된 게임입니다.");

        this.state = GameState.RUNNING;
        racingCars.move();

        currentRound++;
        snapShot.recordRoundState(getCurrentRoundState(), currentRound);

        if(currentRound == round)
            this.state = GameState.FINISHED;
    }


    public void reset() {
        racingCars.reset();
        snapShot.reset();
        this.state = GameState.READY;
        this.currentRound = DEFAULT_ROUND;
    }



    public String[] getCurrentRoundState() {
        return racingCars.getCurrentRoundState();
    }

    public String[] getRoundState(int round) {
        return snapShot.getRoundState(round);
    }


    public String[] getWinners() {
        if(state != GameState.FINISHED)
            throw new IllegalStateException("[ERROR] 경주가 아직 진행중에 있습니다.");

        return racingCars.getLeaders();
    }


    public int getCurrentRound() {
        return currentRound;
    }

    public int getRound() {
        return round;
    }

    public GameState getState() {
        return state;
    }

    public boolean isFinished() {
        return state == GameState.FINISHED;
    }

    public String[] getParticipants() {
        return racingCars.getParticipantNames();
    }



    private RoundSnapShot initializeRoundSnapShot() {
        String[] currentRoundState = getCurrentRoundState();
        return new RoundSnapShot(round, racingCars.getNumOfParticipant(), currentRoundState);
    }



    private int validateRound(int round) {
        if(round <= 0)
            throw new IllegalArgumentException("[ERROR] 라운드는 0보다 커야 합니다. round=%d".
                    formatted(round));
        return round;
    }
}
