package SmallProgs.Kurs_Java8_ZaczProg.L06_BiPredicate;

/** https://github.com/ZacznijProgramowac/KursJava8/blob/20cc4ac72b56115926766a2c458541911f302673/src/main/java/data/Book.java */

public class Book {

    public Book(double price, String cover) {
        this.price = price;
        this.cover = cover;
    }

    public double price;
    public String cover;

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Book book = (Book) o;

        if (Double.compare(book.price, price) != 0)
            return false;
        return cover != null ? cover.equals(book.cover) : book.cover == null;
    }

    @Override public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        return result;
    }
}