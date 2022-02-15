package ch05_stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Practice {
    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("mario", "Milan");
        Trader alan = new Trader("alan", "Cambridge");
        Trader brian = new Trader("brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1번 - 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리리
        List<Transaction> transaction2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        System.out.println("transaction2011 = " + transaction2011);

        // 2번 - 거래자가 근무하는 모든 도시를 중복없이 나열
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());

        System.out.println("cities = " + cities);

        // 3번 - Cambridge 에서 근무하는 모든 거래자를 찾아 이름순으로 정렬
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        System.out.println("traders = " + traders);

        // 4번 - 모든 거래자의 이름을 알파벳순으로 정렬
        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct() // 각각의 이름을 하나의 문자열로 연결하여 모든 이름 연결 (평면화)
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        System.out.println("traderStr = " + traderStr);

        // 5번 - 밀라노에 거래자가 있는가?
        boolean haveMilanTrader = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader()
                        .getCity()
                        .equals("Milan"));

        System.out.println("haveMilanTrader = " + haveMilanTrader);

        // 6번 - Cambridge 에 거주하는 거래자의 모든 트랜잭션값을 출력
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 7번 - 전체 트랜잭션 중 최댓값
        Optional<Integer> highestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        System.out.println("highestValue = " + highestValue);

        // 8번 - 전체 트랜잭션 중 최소값
        Optional<Transaction> smallestTransaction = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        System.out.println("smallestTransaction = " + smallestTransaction);
    }
}

