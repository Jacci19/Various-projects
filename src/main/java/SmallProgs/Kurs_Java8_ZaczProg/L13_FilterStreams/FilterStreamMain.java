package SmallProgs.Kurs_Java8_ZaczProg.L13_FilterStreams;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**    http://zacznijprogramowac.net/streams-i-uzycie-filter-distinct-limit-oraz-skip/
 https://github.com/ZacznijProgramowac/KursJava8/commit/b76af93df1509833dd284458837aaa684e11e779            */

public class FilterStreamMain {



    public static void main(String[] args) {
        //Wykorzystuję swój interfejs funkcjonalny do tworzenia książek
        BookCreator<Book> creatorNew = Book::new;

        List<Book> listBooks = Arrays.asList(
                new Book(39.99, "Czysty kod", "twarda", false),
                new Book(49.99, "Pani jeziora", "miekka", true),
                new Book(49.99, "Pani jeziora", "miekka", true),
                new Book(19.99, "PHP w akcji", "miekka", false),
                new Book(29.99, "Bajki", "twarda", true),
                new Book(19.99, "Zarty programistow", "miekka", true));


        listBooks.stream().filter(book -> book.isPromotion).forEach(book -> System.out.println(book));

        System.out.println("----");

        List<Book> newList = listBooks.stream()
                .filter(book -> book.isPromotion)
                .distinct()
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());

        newList.forEach(book -> System.out.println(book));
    }
}