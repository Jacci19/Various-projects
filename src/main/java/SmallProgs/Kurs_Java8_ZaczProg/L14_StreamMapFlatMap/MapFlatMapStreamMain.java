package SmallProgs.Kurs_Java8_ZaczProg.L14_StreamMapFlatMap;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**     http://zacznijprogramowac.net/stream-i-uzycia-map-oraz-flatmap/
 https://github.com/ZacznijProgramowac/KursJava8/blob/b37f3403e009d388f71aee401607886e4603ad65/src/main/java/streams/MapFlatMapStreamMain.java           */

public class MapFlatMapStreamMain {

    public static void main(String[] args) {
        List<Book> listBooks = Arrays.asList(
                new Book(39.99, "Czysty kod", "twarda", false),
                new Book(49.99, "Pani jeziora", "miekka", true),
                new Book(49.99, "Pani jeziora", "miekka", true),
                new Book(19.99, "PHP w akcji", "miekka", false),
                new Book(29.99, "Bajki", "twarda", true),
                new Book(19.99, "Zarty programistow", "miekka", true));


        List<Double> pricesList = listBooks.stream()
                .map(Book::getPrice)
                .collect(Collectors.toList());


        List<String> pricesListString = listBooks.stream()
                .map(Book::getPrice)
                .map(String::valueOf)
                .collect(Collectors.toList());


        //nowa lista
        List<Book> listBooks2 = Arrays.asList(
                new Book(39.99, "Czysty kod", "twarda", false),
                new Book(49.99, "Pani jeziora", "miękka", true),
                new Book(49.99, "Pani jeziora", "miękka", true));

        //lista z listami książek
        List<List<Book>> collectionOListBooks = Arrays.asList(listBooks, listBooks2);

        //Na tym uzyjemy flat map
        Stream<List<Book>> streamOfListBooks = collectionOListBooks.stream();

        List<Book> uniqueListOfBooks = streamOfListBooks.flatMap(books -> books.stream()).distinct().collect(Collectors.toList());


        System.out.println("Unikalna lista");

        uniqueListOfBooks.forEach(book -> {
            System.out.println(book);
        });


        List<Book> selectedList = collectionOListBooks.stream()
                .flatMap(books -> books.stream())
                .distinct()
                .collect(Collectors.toList());


    }


}