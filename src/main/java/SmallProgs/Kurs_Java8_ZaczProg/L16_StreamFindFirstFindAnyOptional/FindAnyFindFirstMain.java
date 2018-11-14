package SmallProgs.Kurs_Java8_ZaczProg.L16_StreamFindFirstFindAnyOptional;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**     http://zacznijprogramowac.net/steram-i-uzycie-findfirst-findany-oraz-optional-16/
 https://github.com/ZacznijProgramowac/KursJava8/blob/6055c3b44a8fac8989a139c393640f6d64379b87/src/main/java/streams/FindAnyFindFirstMain.java     */

public class FindAnyFindFirstMain {

    public static void main(String[] args) throws NotFound {
        List<Book> listBooks = Arrays.asList(
                new Book(39.99, "Czysty kod", "twarda", false),
                new Book(49.99, "Pani jeziora", "miekka", false),
                new Book(19.99, "PHP w akcji", "miekka", true),
                new Book(29.99, "Bajki", "twarda", false),
                new Book(19.99, "Żarty programistów", "miekka", true));

        Optional<Book> optional = listBooks.stream().filter(Book::isPromotion).findFirst();
        System.out.println(optional);

        //sprawdza czy obiekt istnieje
        boolean isBook = optional.isPresent();

        //sprawdza czy istnieje i zmienia jego wartosc
        optional.ifPresent(book -> book.setPromotion(false));

        //pobiera obiekt
        Book book = optional.get();

        //zwraca wartość jeżeli jest, jeżeli nie tworzy nową
        Book newBook = optional.orElse(new Book(9.99, "Nowa książka w promocji", "miękka", true));

        //Jeżeli nie ma wyniku, możemy rzucić wyjątek
        Book bookOrException = optional.orElseThrow(NotFound::new);

    }

}