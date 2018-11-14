package SmallProgs.Kurs_Java8_ZaczProg.L14_StreamMapFlatMap;

/**    https://github.com/ZacznijProgramowac/KursJava8/blob/ff5b7b424eedd9ce9ac79af5bbba0a8558587058/src/main/java/data/Book.java     */
//Klasa skopiowana z L12

public class Book implements Cloneable{

    public Book() {
    }

    public Book(double price, String title, String cover) {
        this.price = price;
        this.title = title;
        this.cover = cover;
    }

    public Book(double price, String title, String cover, boolean isPromotion) {
        this.title = title;
        this.price = price;
        this.cover = cover;
        this.isPromotion = isPromotion;
    }

    public String title;
    public double price;
    public String cover;
    public boolean isPromotion;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isPromotion() {
        return isPromotion;
    }

    public void setPromotion(boolean promotion) {
        isPromotion = promotion;
    }
}
