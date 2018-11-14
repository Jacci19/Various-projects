package SmallProgs.Kurs_Java8_ZaczProg.L07_ConsumerFunctionUnaryOperator;

public class Book implements Cloneable{



    public Book(double price, String title, String cover) {
        this.price = price;
        this.title = title;
        this.cover = cover;
    }

    public String title;
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

    @Override public String toString() {
        return "Book{" + "title='" + title + '\'' + ", price=" + price + ", cover='" + cover + '\'' + '}';
    }

    public Book clone()  {
        try {
            return  (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}