package racingcar.domain;


public class RoundSnapShot {

    private static final int DEFAULT_ROUND = 0;


    private final String[][] roundStates;

    private int currentRound;

    private final int round;



    public RoundSnapShot(int round, int participants, String[] initialState) {
        if(round < 0 )
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 양의 정수여야 합니다.");

        if(participants < 0)
            throw new IllegalArgumentException("[ERROR] 참여자는 양의 정수여야 합니다.");

        this.currentRound = DEFAULT_ROUND;
        this.round = round;
        this.roundStates = new String[round+1][participants];
        this.roundStates[0] = initialState;
    }


    public String[] getRoundState(int searchRound) {
        if(searchRound > currentRound)
            throw new IllegalArgumentException("[ERROR] 조회할 라운드가 진행된 라운드 보다 큽니다. searchRound=%d, currentRound=%d".
                    formatted(searchRound, currentRound));

        if(searchRound < 0)
            throw new IllegalArgumentException("[ERROR] searchRound는 양의 정수여야 합니다. searchRound=%d".
                    formatted(searchRound));

        return roundStates[searchRound];
    }

    public void recordRoundState(String[] snapShot, int round) {
        if(snapShot == null)
            throw new IllegalArgumentException("[ERROR] snapshot이 null 입니다.");

        if(round != currentRound + 1)
            throw new IllegalArgumentException("[ERROR] 올바르지 않는 round입력입니다. required=%d, input=%d".
                    formatted(currentRound+1, round));

        this.currentRound++;
        roundStates[round] = snapShot;
    }


    public void reset() {
        this.currentRound = DEFAULT_ROUND;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getRound() {
        return round;
    }
}
