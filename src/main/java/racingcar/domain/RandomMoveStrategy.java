package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomMoveStrategy implements MoveStrategy{

    private static final int MOVE_THRESHOLD = 4;

    private static final int RAND_MIN = 0;

    private static final int RAND_MAX = 9;


    @Override
    public boolean movable() {
        return Randoms.pickNumberInRange(RAND_MIN, RAND_MAX) >= MOVE_THRESHOLD;
    }
}
