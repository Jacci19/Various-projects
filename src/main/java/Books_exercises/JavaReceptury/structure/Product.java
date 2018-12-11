package Books_exercises.JavaReceptury.structure;

// BEGIN main
public class Product {
    String title;
    String artist;
    Media  media;

    public Product(String artist, String title, Media media) {
        this.title = title;
        this.artist = artist;
        this.media = media;
    }
    
    @Override
    public String toString() {
        switch (media) {
        case BOOK:
            return title + " to książka.";
        case MUSIC_CD:
            return title + " to płyta CD.";
        case MUSIC_VINYL:
            return title + " to relikt z ery winylu.";
        case MOVIE_VHS:
            return title + " to stara taśma wideo.";
        case MOVIE_DVD:
            return title + " jest na DVD.";
        default:
            return title + ": Nieznany rodzaj medium " + media;
        }        
    }
}
// END main
