package racingcar.domain;


public class RoundSnapShot {

    private final String[][] roundStates;

    private int currentRound;

    private int round;



    public RoundSnapShot(int round, int participants) {
        if(round < 0 )
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 양의 정수여야 합니다.");

        this.currentRound = 0;
        this.round = round;
        this.roundStates = new String[round][participants];
    }


    public String[] getRoundState(int searchRound) {
        if(searchRound > currentRound)
            throw new IllegalArgumentException("[ERROR] 조회할 라운드가 진행된 라운드 보다 큽니다. searchRound=%d, currentRound=%d".
                    formatted(searchRound, currentRound));

        return roundStates[searchRound];
    }

    public void recordRoundState(String[] snapShot, int round) {
        if(snapShot == null)
            throw new IllegalArgumentException("[ERROR] snapshot이 null 입니다.");

        if(round != currentRound + 1)
            throw new IllegalArgumentException("[ERROR] round입력이 잘못되었습니다. required=%d, input=%d".
                    formatted(currentRound+1, round));

        roundStates[round] = snapShot;
    }

    public void reset() {
        this.currentRound = 0;
    }

}
