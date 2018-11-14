package SmallProgs.Kurs_Java8_ZaczProg.L12_Streams_introduction;


import java.util.*;
import java.util.stream.Collectors;

/**        https://github.com/ZacznijProgramowac/KursJava8/blob/ff5b7b424eedd9ce9ac79af5bbba0a8558587058/src/main/java/streams/StreamMain.java
 http://zacznijprogramowac.net/wstep-do-streams-w-java-8/                    */

public class StreamMain {


    public static void main(String[] args) {

        //Wykorzystuję swój interfejs funkcjonalny do tworzenia książek
        System.out.println("\n________Bez uzycia Stream_________");
        BookCreator<Book> creatorNew = Book::new;
        Book book = creatorNew.create(11.99, "Bajki", "twarda");


        List<Book> list = Arrays.asList(
                creatorNew.create(39.99, "Czysty kod", "twarda"),
                creatorNew.create(49.99, "Pani jeziora", "miekka"),
                creatorNew.create(19.99, "PHP w akcji", "miekka"),
                creatorNew.create(29.99, "Bajki", "twarda"),
                creatorNew.create(19.99, "Zarty programistow", "miekka"));

        //Jak to było przed Java 8
        List<Book> filteredBook = new ArrayList<>();
        for (Book b : list) {
            if (b.price < 49.99) {
                filteredBook.add(b);
            }
        }

        Collections.sort(filteredBook, new Comparator<Book>() {
            @Override public int compare(Book o1, Book o2) {
                return Double.compare(o1.price, o2.price);
            }
        });

        List<String> namesBooks = new ArrayList<>();
        for (Book b : filteredBook) {
            namesBooks.add(b.title);
        }

        for (String b : namesBooks) {
            System.out.println(b);
        }

        System.out.println("\n________Z uzyciem Stream_________");
        List<String> titlesBooks = list.stream()
                .filter(b -> b.price < 49.99)
                .sorted(Comparator.comparing(Book::getPrice))
                .map(b -> b.getTitle()).collect(Collectors.toList());

        titlesBooks.forEach(b -> System.out.println(b));


    }
}