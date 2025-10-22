package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.dto.GameRequest;

public class InputView {


    private static final String DELIMITER_REGEX = ",";



    public static String[] inputCarNames() {

        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");

        String input = Console.readLine();

        if(input == null || input.isEmpty())
            throw new IllegalArgumentException("[ERROR] 입력이 null 또는 empty입니다.");

        String[] carNames = input.split(DELIMITER_REGEX);
        validateCarNames(carNames);

        return carNames;
    }


    public static int inputRound() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        try {
            int round = Integer.parseInt(Console.readLine());

            return round;
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 정수여야 합니다.");

        }
    }

    private static void validateCarNames(String[] carNames) {
        for(String carName : carNames) {
            if(carName == null || carName.isEmpty())
                throw new IllegalArgumentException("[ERROR] carName이 null 또는 empty입니다.");
        }
    }
}
