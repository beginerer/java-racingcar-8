package racingcar.domain;

import java.util.HashMap;
import java.util.Map;

public class RacingGame {

    private RacingCar[] racingCars;

    private int round;

    private GameState state;


    public RacingGame(String[] carNames, int round) {
        this.racingCars = resolveCarNames(carNames);
        this.round = validateRound(round);
        this.state = GameState.READY;
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
        Map<String, Integer> map = new HashMap<>();

        String[] uniqueCarName = new String[carNames.length];

        for(String carName : carNames) {
            map.put(carName, map.getOrDefault(carName,0) + 1);
            int count = map.get(carName);


        }
    }

    private String




    private int validateRound(int round) {
        if(round <= 0)
            throw new IllegalArgumentException("[ERROR] 라운드는 0보다 커야 합니다. round=%d".
                    formatted(round));
        return round;
    }


}
