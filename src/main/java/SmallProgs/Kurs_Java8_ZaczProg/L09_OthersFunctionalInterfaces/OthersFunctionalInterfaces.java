package SmallProgs.Kurs_Java8_ZaczProg.L09_OthersFunctionalInterfaces;

import java.util.function.*;

/**     https://github.com/ZacznijProgramowac/KursJava8/blob/f85f2effc182ac5b59d83a5e05424c0e4e438ddb/src/main/java/interfacesAll/Others.java
 http://zacznijprogramowac.net/pozostale-interfejsy-funkcjonalne/          */

public class OthersFunctionalInterfaces {


    public static void main(String[] args) {
        System.out.println("____________________________________ObjDoubleConsumer______________________");
        ObjDoubleConsumer<String> objDoubleConsumer = (s, d) -> System.out.println(s + d);
        objDoubleConsumer.accept("PI rowna sie: ", 3.14);

        System.out.println("____________________________________ ObjIntConsumer________________________");
        ObjIntConsumer<Double> doubleObjIntConsumer = (d, i) -> System.out.println("I tak jestem PI: " + (i + d));
        doubleObjIntConsumer.accept(0.14, 3);

        System.out.println("____________________________________ObjLongConsumer________________________");
        ObjLongConsumer<String> stringObjLongConsumer = (s, d) -> System.out.println(s + d);
        stringObjLongConsumer.accept("Jestem najdluzsza liczba swiata!: ", Long.MAX_VALUE);

        System.out.println("____________________________________ToDoubleBiFunction_____________________");
        ToDoubleBiFunction<Integer, Integer> toDoubleBiFunction = (x, y) -> (x + y) / 3.14;
        System.out.println(toDoubleBiFunction.applyAsDouble(2, 3));

        System.out.println("____________________________________ToDoubleFunction_______________________");
        ToDoubleFunction<String> toDoubleFunction = (x) -> Double.valueOf(x);
        System.out.println(toDoubleFunction.applyAsDouble("3.14"));

        System.out.println("____________________________________DoubleBinaryOperator___________________");
        DoubleBinaryOperator doubleBinaryOperator = (x, y) -> x + y;
        System.out.println(doubleBinaryOperator.applyAsDouble(3.0, 0.14));

        System.out.println("____________________________________DoubleConsumer_________________________");
        DoubleConsumer doubleConsumer = x -> System.out.println(x + 3.0);
        doubleConsumer.accept(0.14);

        System.out.println("____________________________________DoubleFunction_________________________");
        DoubleFunction<String> doubleFunction = x -> "Jestem stringiem PI: " + x;
        System.out.println(doubleFunction.apply(3.14));

        System.out.println("____________________________________DoublePredicate________________________");
        DoublePredicate doublePredicate = x -> x > 3.14;
        System.out.println("Czy jestem wieksza od PI? " + doublePredicate.test(2.14));

        System.out.println("____________________________________DoubleSupplier_________________________");
        DoubleSupplier doubleSupplier = () -> 3.14;
        System.out.println("Dostarczam PI: " + doubleSupplier.getAsDouble());

        System.out.println("____________________________________DoubleToIntFunction____________________");
        DoubleToIntFunction doubleToIntFunction = x -> (int) Math.round(x);
        System.out.println("Jestem okragle PI: " + doubleToIntFunction.applyAsInt(3.14));

        System.out.println("____________________________________DoubleToLongFunction___________________");
        DoubleToLongFunction doubleToLongFunction = x -> Math.round(x);
        System.out.println("Jestem okragle PI jako long: " + doubleToLongFunction.applyAsLong(3.14));

        System.out.println("____________________________________DoubleUnaryOperator____________________");
        DoubleUnaryOperator doubleUnaryOperator = x -> x*2;
        System.out.println("Jestem podwojone PI: " + doubleUnaryOperator.applyAsDouble(3.14));
    }
}