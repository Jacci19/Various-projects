package SmallProgs.Kurs_Java8_ZaczProg.L15_StreamAnyMatchAllMatchNoneMatch;


import java.util.Arrays;
import java.util.List;


/**      https://github.com/ZacznijProgramowac/KursJava8/blob/7a9bbcce6aaf70af7627c90856eaac186eb72a30/src/main/java/streams/MatchMain.java
 http://zacznijprogramowac.net/stream-i-uzycie-anymatch-allmatch-oraz-nonematch-15/           */

public class MatchMain {

    public static void main(String[] args) {
        List<Book> listBooks = Arrays.asList(
                new Book(39.99, "Czysty kod", "miękka", false),
                new Book(49.99, "Pani jeziora", "miękka", true),
                new Book(49.99, "Pani jeziora", "miękka", true),
                new Book(19.99, "PHP w akcji", "miękka", false),
                new Book(29.99, "Bajki", "miękka", true),
                new Book(19.99, "Żarty programistów", "miękka", true));

        boolean isPromotion = listBooks.stream().anyMatch(book -> book.isPromotion);
        System.out.println(isPromotion);

        boolean isLowerPrice = listBooks.stream().allMatch(book -> book.price < 39.00);
        System.out.println(isLowerPrice);

        boolean isHardCover = listBooks.stream().noneMatch(book -> book.cover.equals("twarda"));
        System.out.println(isHardCover);

    }
}