package Books_exercises.JavaReceptury.structure;

// BEGIN main
/** Przykład typu wyliczeniowego, w którym przesłonięto metodę. */
public enum MediaFancy {
    /** Stała typu wyliczeniowego reprezentująca książki, 
     * dla której przesłonięto metodę toString(). */
    BOOK {
        public String toString() { return "Książka"; }
    },
    /** Stała dla muzycznych płyt CD. */
    MUSIC_CD,
    /** ... */
    MUSIC_VINYL,
    MOVIE_VHS,
    MOVIE_DVD;

    /** Zazwyczaj dyskredytuje się implementowanie w typach 
     * wyliczeniowych metody main(); proszę wybaczyć, że zrobiłem
     * to w tej małej klasie przykładowej.
     */
    public static void main(String[] args) {
        MediaFancy[] data =  { BOOK, MOVIE_DVD, MUSIC_VINYL };
        for (MediaFancy mf : data) {
            System.out.println(mf);
        }
    }
}
// END main
