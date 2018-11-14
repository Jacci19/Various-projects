package SmallProgs.Kurs_Java8_ZaczProg.L12_Streams_introduction;

/** https://github.com/ZacznijProgramowac/KursJava8/blob/ff5b7b424eedd9ce9ac79af5bbba0a8558587058/src/main/java/methodReference/BookCreator.java      */


@FunctionalInterface
public interface BookCreator<Book> {

    Book create(double price, String title, String cover);
}
