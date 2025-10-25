package racingcar.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RacingCars {

    private final RacingCar[] racingCars;


    public RacingCars(String[] carNames) {
        this.racingCars = resolveCarNames(carNames);
    }


    public void move() {
        Arrays.stream(racingCars).forEach(RacingCar::move);
    }

    public void reset() {
        Arrays.stream(racingCars).forEach(RacingCar::reset);
    }

    public int getMaxPosition() {
        return Arrays.stream(racingCars).mapToInt(RacingCar::getPosition)
                .max().orElseThrow(() -> new IllegalStateException("[ERROR] maxPosition을 찾을 수 없습니다."));
    }

    public String[] getLeaderNames() {
        int maxPosition = getMaxPosition();

        List<String> leaders = Arrays.stream(racingCars).filter(car -> {
            return car.getPosition() == maxPosition;
        }).map(RacingCar::getName).toList();

        return leaders.toArray(new String[0]);
    }


    public String[] getCurrentRoundState() {
        String[] roundSnapshot = new String[racingCars.length];

        for(int i=0; i<racingCars.length; i++) {
            roundSnapshot[i] = racingCars[i].toString();
        }
        return roundSnapshot;
    }

    public String[] getParticipantNames() {
        return Arrays.stream(racingCars).map(RacingCar::getName).toArray(String[]::new);
    }

    public int getNumOfParticipant() {
        return racingCars.length;
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
            String suffix = String.format("%d", count-1);
            return carName + suffix;
        }
        return carName;
    }


}
