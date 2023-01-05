package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_SIZE;
import static lotto.domain.LottoNumbers.makeLottoNumbers;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private final LottoNumbers lottoNumbers;

    private static boolean isNotFull(List<Integer> numbers) {
        return numbers.size() != LOTTO_SIZE;
    }

    private static void addGeneratedNumber(List<Integer> numbers, int generate) {
        if (!numbers.contains(generate)) {
            numbers.add(generate);
        }
    }

    public static Lotto makeLottoAuto(GeneratePolicy generatePolicy) {
        List<Integer> numbers = new ArrayList<>();
        while (isNotFull(numbers)) {
            addGeneratedNumber(numbers, generatePolicy.generate());
        }
        return makeLotto(numbers);
    }

    public static Lotto makeLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }


    private Lotto(List<Integer> numbers) {
        this.lottoNumbers = makeLottoNumbers(numbers);
    }

    public LottoResult match(LottoAnswer lottoAnswer) {
        int match = lottoAnswer.match(lottoNumbers);
        boolean hasBonusBall = lottoAnswer.hasBonusBall(lottoNumbers);
        return LottoResult.makeResult(match, hasBonusBall);
    }

    public String lottoToString() {
        return lottoNumbers.toString();
    }

}