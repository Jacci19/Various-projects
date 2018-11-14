package SmallProgs.Kurs_Java8_ZaczProg.L06_BiPredicate;

import java.util.function.BiPredicate;

/**         http://zacznijprogramowac.net/omowienie-interfejsu-bipredicate-6/
 https://github.com/ZacznijProgramowac/KursJava8/blob/20cc4ac72b56115926766a2c458541911f302673/src/main/java/interfacesAll/BiPredicateMain.java      */

public class BiPredicateMain {

    public static void main(String[] args) {

        Book book1 = new Book(39.99, "twarda");
        Book book2 = new Book(29.99, "twarda");

        BiPredicate<Book, Book> isMoreExpensive = (bookFirst, bookSecond) -> bookFirst.price > bookSecond.price;

        if (isMoreExpensive.test(book1, book2)) {
            System.out.println("Tak! Pierwsza ksiazka jest drozsza.");
        }

        BiPredicate<Book, Book> sameCover = (bookFirst, bookSecond) -> bookFirst.cover.equals(bookSecond.cover);

        if (sameCover.and(isMoreExpensive).test(book1, book2)) {
            System.out.println("Takie same okladki i ksiazka pierwsza jest drozsza.");
        }

        boolean i = isMoreExpensive.negate().test(book1, book2);
        System.out.println("Czy druga jest tansza? " + i);



        boolean or = sameCover.or(isMoreExpensive).test(book1, book2);
        System.out.println("Czy jest drozsza lub ta sama okladka? " + or);


    }
}