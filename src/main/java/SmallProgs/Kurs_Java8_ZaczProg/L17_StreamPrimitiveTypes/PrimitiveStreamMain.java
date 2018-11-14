package SmallProgs.Kurs_Java8_ZaczProg.L17_StreamPrimitiveTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**     https://github.com/ZacznijProgramowac/KursJava8/blob/2ca7b3bc6d118a9943f932dea1d001f5c21603a4/src/main/java/primitiveStream/PrimitiveStreamMain.java
 http://zacznijprogramowac.net/stream-i-prymitywne-typy-danych-16/                                       */

public class PrimitiveStreamMain {


    public static void main(String[] args) {
        IntStream differentNumbers = IntStream.of(1, 9, 3, 7, 3, 4).sorted();
        System.out.println("IntStream.of:");
        differentNumbers.forEach(System.out::println);                                              //wynik: 1 3 3 4 7 9

        IntStream rangeNumber = IntStream.range(1, 100).filter(i -> i % 2 == 0);
        System.out.println("IntStream.range:");
        rangeNumber.forEach(System.out::println);

        IntStream rangeNumberClosed = IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 1);
        System.out.println("IntStream.rangeClosed:");
        rangeNumberClosed.forEach(System.out::println);

        OptionalInt generateNumber = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(500)).limit(10).max();
        System.out.println("IntStream.generate:");
        System.out.println(generateNumber.getAsInt());

        IntStream numberIterate = IntStream.iterate(0, n -> n + 4).limit(5);
        System.out.println("IntStream.iterate:");
        numberIterate.forEach(System.out::println);                                                 //wynik: 0 4 8 12 16

        List<Integer> bigIntegers = new ArrayList<>();
        bigIntegers.add(new Integer(1));
        bigIntegers.add(new Integer(2));
        bigIntegers.add(new Integer(3));

        IntStream smallInteger = bigIntegers.stream().mapToInt(Integer::valueOf);
        System.out.println("mapToInt:");
        smallInteger.forEach(System.out::println);

        int[] arrayInt = IntStream.range(1, 100).toArray();
        Stream<Integer> streamInteger = Arrays.stream(arrayInt).boxed();
        System.out.println("Boxed:");
        streamInteger.forEach(System.out::println);
    }
}