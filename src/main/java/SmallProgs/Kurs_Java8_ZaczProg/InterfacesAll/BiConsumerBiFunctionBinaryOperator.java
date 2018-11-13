package SmallProgs.Kurs_Java8_ZaczProg.InterfacesAll;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**     http://zacznijprogramowac.net/omowienie-interfejsow-biconsumer-bifunction-binaryoperator/            */

public class BiConsumerBiFunctionBinaryOperator {


    public static void main(String[] args) {

        //BiConsumer_____________________________________________________
        BiConsumer<Double, Double> biConsumer = (x, y) -> {
            System.out.println("Dodawanie: " + (x + y));
        };
        biConsumer.accept(1.0, 2.0);

        BiConsumer<Double, Double> biConsumer1 = (x, y) -> {
            System.out.println("Mnozenie: " + (x * y));
        };

        BiConsumer<Double, Double> biConsumer2 = (x, y) -> {
            System.out.println("Dzielenie: " + (x / y));
        };
        biConsumer.andThen(biConsumer1).andThen(biConsumer2).accept(3.0, 2.0);
        // END


        //BiFunction_____________________________________________________
        BiFunction<Double, Double, Double> biFunction = (x, y) -> {
            return x + y;
        };
        System.out.println(biFunction.apply(1.0, 1.0));

        Function<Double,Double> function = x-> x + Math.PI;

        System.out.println(biFunction.andThen(function).apply(1.0,1.0));
        // END

        ///BinaryOperator_____________________________________________________
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
        System.out.println(binaryOperator.apply(4,5));

        BinaryOperator<Integer> biByMin = BinaryOperator.minBy(Integer::compareTo);
        System.out.println(biByMin.apply(5, 7));

        BinaryOperator<Integer> biByMax = BinaryOperator.maxBy(Integer::compareTo);
        System.out.println(biByMax.apply(123123, 897797789));
        // END

    }

}
