package SmallProgs.Kurs_Java8_ZaczProg.PredicateMain;

import java.util.function.Predicate;

/**          http://zacznijprogramowac.net/omowienie-interfejsu-predicate-5/             */

public class PredicateMain {



    public static void main(String[] args) {
        Book book = new Book(39.99, "Hobbit","twarda");
        Predicate<Book> checkPrice = book1 -> book1.price > 29.99;

        if (checkPrice.test(book)) {
            System.out.println("Drogo!");
        }

        Predicate<Book> checkCover = book2 -> book2.cover.equals("twarda");

        if (checkCover.and(checkPrice).test(book)) {
            System.out.println("Tanio!");
        }

        if (!checkPrice.negate().test(book)) {
            System.out.println("A jednak tanio!");
        }

        if (checkCover.or(checkPrice).test(book)) {
            System.out.println("Tanio lub drogo!");
        }

        Predicate<Book> i = Predicate.isEqual(new Book(39.99, "Pani jeziora", "twarda"));
        System.out.println("Czy ta sama książka? " + i.test(book));

    }
}