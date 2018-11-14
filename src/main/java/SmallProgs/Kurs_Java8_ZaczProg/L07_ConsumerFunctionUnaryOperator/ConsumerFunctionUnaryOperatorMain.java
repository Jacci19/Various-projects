package SmallProgs.Kurs_Java8_ZaczProg.L07_ConsumerFunctionUnaryOperator;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**   https://github.com/ZacznijProgramowac/KursJava8/blob/f89b0abe0182b85cde359f85f7116f0687294ba6/src/main/java/interfacesAll/ConsumerFunctionUnaryOperatorMain.java
 http://zacznijprogramowac.net/omowienie-interfejsow-consumer-function-unaryoperator-7/                                  */

public class ConsumerFunctionUnaryOperatorMain {

    public static void main(String[] args) {

        List<Book> list = Arrays.asList(
                new Book(19.99, "Czysty kod", "twarda"),
                new Book(29.99, "Pani jeziora", "miekka"),
                new Book(39.99, "Hobbit","twarda"));

        //Consumer
        System.out.println("\n______________Consumer______________");
        Consumer<Book> printTitle = (book) -> System.out.println(book.title);
        list.forEach(printTitle);

        System.out.println();


        Consumer<Book> printCover = (book) -> System.out.println(book.cover);
        list.forEach(printTitle.andThen(printCover));



        //Function
        System.out.println("\n______________Function______________");
        Book book1 = new Book(19.99, "Czysty kod", "twarda");
        Function<Book, String> returnPrice = (book) -> Double.toString(book.price);

        System.out.println(returnPrice.apply(book1));

        Function<String, String> addString = (s) -> "Cena: " +s;
        System.out.println(addString.compose(returnPrice).apply(book1));

        System.out.println(returnPrice.andThen(addString).apply(book1));

        Function<Book, Book> returnBook = Function.identity();
        Function<Book, Book> returnBook2 = book -> book;

        System.out.println(returnBook2.apply(book1));




        //UnaryOperator
        System.out.println("\n______________UnaryOperator______________");
        Book someBook = new Book(19.99, "Czysty kod", "twarda");
        UnaryOperator<Book> calculateDiscount = book -> book.clone();

        Book bookCopy = calculateDiscount.apply(someBook);
        System.out.println("Czy to te same obiekty? ");
        System.out.println(someBook == bookCopy);

    }
}