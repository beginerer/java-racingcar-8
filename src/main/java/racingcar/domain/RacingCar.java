package racingcar.domain;


public class RacingCar {

    private static final int NAME_MAX_LEN = 5;


    private final String name;

    private int position;

    private final MoveStrategy strategy;


    public RacingCar(String name) {
        this(name, new RandomMoveStrategy());
    }

    public RacingCar(String name, MoveStrategy strategy) {
        this.name = validateCarName(name);
        this.position = 0;
        this.strategy = strategy;
    }



    public void move() {
        boolean movable = strategy.movable();

        if(movable) {
            this.position++;
        }
    }


    public void reset() {
        this.position = 0;
    }


    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }



    private String validateCarName(String carName) {
        if(carName == null || carName.isBlank())
            throw new IllegalArgumentException("[ERROR] 자동차 이름이 비어 있습니다.");


        if(carName.length() >  NAME_MAX_LEN)
            throw new IllegalArgumentException("[ERROR] 자동차 이름은 5글자 이하만 가능합니다. name=%s length=%d".
                    formatted(carName, carName.length()));

        return carName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" : ");
        sb.append("-".repeat(position));
        return sb.toString();
    }

}
