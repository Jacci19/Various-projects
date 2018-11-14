package SmallProgs.Kurs_Java8_ZaczProg.L10_MethodReference;


/**  https://github.com/ZacznijProgramowac/KursJava8/blob/4fee4866d9b289a464ad880a82d2004c1ee978d7/src/main/java/methodReference/BookCreator.java * https://www.youtube.com/zacznijprogramowac   */

@FunctionalInterface
public interface BookCreator<Book> {

    Book create(double price, String title, String cover);
}
