package Books_exercises.JavaReceptury.structure;

/** Kategorie sprzedawanych produktów.
 */
public enum Media {

    BOOK, MUSIC_CD, MUSIC_VINYL, MOVIE_VHS, MOVIE_DVD;

    public static Media getMedia(String s) {
        return valueOf(s.toUpperCase());
    }
    public static Media getMedia(int n){
        return values()[n];
    }
}