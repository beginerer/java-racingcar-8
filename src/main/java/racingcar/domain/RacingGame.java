package racingcar.domain;


import java.util.*;


public class RacingGame {

    private final RacingCar[] racingCars;

    private int currentRound;

    private final int round;

    private final RoundSnapShot snapShot;

    private GameState state;




    public RacingGame(String[] carNames, int round) {
        this.racingCars = resolveCarNames(carNames);
        this.round = validateRound(round);
        this.currentRound = 0;
        this.snapShot = new RoundSnapShot(round, carNames.length);
        this.state = GameState.READY;
    }



    public void playAllRounds() {
        if(state == GameState.FINISHED || currentRound >= round)
            throw new IllegalStateException("[ERROR] 이미 종료된 게임입니다.");

        this.state = GameState.RUNNING;

        for (int i=currentRound; i<round; i++) {
            Arrays.stream(racingCars).forEach(RacingCar::move);
            this.currentRound++;
            snapShot.recordRoundState(getCurrentRoundState(), i);
        }
        this.state = GameState.FINISHED;
    }

    public void playRound() {
        if(state == GameState.FINISHED || currentRound >= round)
            throw new IllegalStateException("[ERROR] 이미 종료된 게임입니다.");

        this.state = GameState.RUNNING;

        Arrays.stream(racingCars).forEach(RacingCar::move);
        this.currentRound++;
        snapShot.recordRoundState(getCurrentRoundState(), currentRound);

        if(currentRound == round)
            this.state = GameState.FINISHED;
    }


    public void reset() {
        Arrays.stream(racingCars).forEach(RacingCar::reset);
        snapShot.reset();
        this.state = GameState.READY;
        this.currentRound = 0;
    }



    public String[] getCurrentRoundState() {
        String[] roundSnapshot = new String[racingCars.length];

        for(int i=0; i<racingCars.length; i++) {
            roundSnapshot[i] = racingCars[i].toString();
        }
        return roundSnapshot;
    }


    public String[] getWinners() {
        if(state != GameState.FINISHED)
            throw new IllegalStateException("[ERROR] 경주가 아직 진행중에 있습니다.");

        int maxPosition = Arrays.stream(racingCars).mapToInt(RacingCar::getPosition)
                .max().orElseThrow();

        List<String> winners = Arrays.stream(racingCars).filter(car -> {
            return car.getPosition() == maxPosition;
        }).map(RacingCar::getName).toList();

        return winners.toArray(new String[0]);
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


    private RacingCar[] resolveCarNames(String[] carNames) {
        if(carNames==null)
            throw new IllegalArgumentException("[ERROR] carNames가 null입니다.");

        String[] uniqueCarNames = resolveDuplicateCarName(carNames);

        RacingCar[] racingCars = new RacingCar[uniqueCarNames.length];

        for(int i=0; i<uniqueCarNames.length; i++) {
            String carName = uniqueCarNames[i];
            racingCars[i] = new RacingCar(carName);
        }

        return racingCars;
    }



    private String[] resolveDuplicateCarName(String[] carNames) {
        Map<String, Integer> counts = new HashMap<>();

        String[] uniqueCarNames = new String[carNames.length];

        for(int i=0; i< carNames.length; i++) {
            String carName = carNames[i];

            counts.put(carName, counts.getOrDefault(carName,0) + 1);
            int count = counts.get(carName);

            uniqueCarNames[i] = markDuplicateName(carName, count);
        }

        return uniqueCarNames;
    }


    private String markDuplicateName(String carName, int count) {
        if(count > 1) {
            String suffix = String.format("(%d)", count-1);
            return carName + suffix;
        }
        return carName;
    }

    private int validateRound(int round) {
        if(round <= 0)
            throw new IllegalArgumentException("[ERROR] 라운드는 0보다 커야 합니다. round=%d".
                    formatted(round));
        return round;
    }
}
