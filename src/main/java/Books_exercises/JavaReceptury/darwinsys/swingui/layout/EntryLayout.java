package Books_exercises.JavaReceptury.darwinsys.swingui.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import com.darwinsys.util.Debug;

/** Prosty menadżer układu, przeznaczony do tworzenia okien 
 * dialogowych służących do wprowadzania danych. Przykład:
 * <PRE>
 *   Użytkownik: _______________
 *        Hasło: _______________
 * </PRE>
 * Najprościej rzecz biorąc menadżer tworzy dwie (lub więcej) kolumn
 * o różnej (lecz stałej) szerokości.
 * <b>Notatka: wszystkie kolumny muszą mieć tą samą wysokość</b>.
 * <P>
 * Przy tworzeniu obiektu tej klasy należy przekazać tablicę zawierającą 
 * liczby odpowiadające procentowej szerokości poszczególnych kolumn 
 * (zapisane jako liczby zmiennoprzecinkowe typu double, wartości ułamkowe
 * z zakresu od 0.1 do 0.9; czyli 40%,60% należy zapisać jako {0.4, 0.6}).
 * Długość przekazanej tablicy jednoznacznie określa ilość kolumn.
 * Szerokość kolumn jest odpowiednio wymuszana.
 * <b>Notatka:</b> Podobnie jak w przypadku menadżera GridLayout, 
 * także i tutaj ilość dodawanych komponentów <B>musi</B> stanowić 
 * wielokrotność ilości kolumn. W przeciwnym przypadku zostanie 
 * zgłoszony wyjątek.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
// package com.darwinsys.swingui.layout;
public class EntryLayout implements LayoutManager {
    /** Tablica szerokości jako dziesiętne wartości ułamkowe (0.4 == 40% itd.). */
    protected final double[] widthPercentages;

    /** Liczba kolumn. */
    protected final int COLUMNS;

    /** Domyślne odstępy */
    protected final static int HPAD = 5, VPAD = 5;
    /** Stosowane odstępy */
    protected final int hpad, vpad;

    /** True jeśli lista szerokości jest poprawna. */
    protected boolean validWidths = false;

    /** Konstruktor klasy EntryLayout umożliwiający podanie szerokości i odstępów.
     * @param relWidths    Tablica liczb double określających szerokości kolumn.
     * @param h            Odstępy poziome między elementami.
     * @param v            Odstępy pionowe między elementami.
     */
    public EntryLayout(double[] relWidths, int h, int v) {
        COLUMNS = relWidths.length;
        widthPercentages = new double[COLUMNS];
        for (int i=0; i<relWidths.length; i++) {
            if (relWidths[i] >= 1.0)
                throw new IllegalArgumentException(
                    "EntryLayout: szerokości muszą być wartościami ułamkowymi < 1");
            widthPercentages[i] = relWidths[i];
        }
        validWidths = true;
        hpad = h;
        vpad = v;
    }

    /** Konstruktor klasy EntryLayout wykorzystujący domyślne szerokości i odstępy.
     * @param relWidths    Tablica liczb double określających szerokości kolumn.
     */
    public EntryLayout(double[] relWidths) {
        this(relWidths, HPAD, VPAD);
    }

    /** Dodaje podany komponent z podanymi ograniczeniami do menadżera 
     * układu. Metoda wymagana przez interfejs LayoutManager, lecz w naszym
     * przypadku nieużywana.
     */
    public void addLayoutComponent(String name, Component comp) {
        // nic nie robi
    }

    /** Metoda usuwa komponent z menadżera układu; jest ona wymagana przez 
     * interfejs LayoutManager, lecz w naszym przypadku nieużywana.
     */
    public void removeLayoutComponent(Component comp)  {
        // nic nie robi
    }

    /** Oblicza preferowaną wielkość panelu na podstawie
     * komponentów dostępnych w jego elemencie nadrzędnym. */
    public Dimension preferredLayoutSize(Container parent)  {
        // System.out.println("preferredLayoutSize");
        return computeLayoutSize(parent, hpad, vpad);
    }

    /** Określa minimalną wielkość panelu na podstawie 
     * komponentów, jakie mają być w nim wyświetlone.
     */
    public Dimension minimumLayoutSize(Container parent)  {
        // System.out.println("minimumLayoutSize");
        return computeLayoutSize(parent, 0, 0);
    }

    /** Szerokości poszczególnych kolumn, określone przez computLayoutSize(). */
    int[] widths;
    /** Wysokości poszczególnych wierszy, określone przez computLayoutSize(). */
    int[] heights;

    /** Obliczamy wielkości wszystkich komponentów. Metoda pomocnicza wykorzystywana
     * przez metody preferredLayoutSize() oraz minimumLayoutSize().
     * @param parent Kontener, w którym będzie umieszczony układ.
     * @param hp Wypełnienie w poziomie (może wynosić 0).
     * @param vp Wypełnienie w pionie (może wynosić).
     */
    protected Dimension computeLayoutSize(Container parent, int hp, int vp) {
        if (!validWidths)
            return null;
        Component[] components = parent.getComponents();
        int preferredWidth = 0, preferredHeight = 0;
        widths = new int[COLUMNS];
        heights = new int[components.length / COLUMNS];
        // System.out.println("Siatka: " + widths.length + ", " + heights.length);

        int i;
        // Przejście pierwsze: obliczamy największą wysokość i szerokość.
        for (i=0; i<components.length; i++) {
            int row = i / widthPercentages.length;
            int col = i % widthPercentages.length;
            Component c = components[i];
            Dimension d = c.getPreferredSize();
            widths[col] = Math.max(widths[col], d.width);
            heights[row] = Math.max(heights[row], d.height);
        }

        // Przejście drugie: łączymy je.
        for (i=0; i<widths.length; i++)
            preferredWidth += widths[i] + hp;
        for (i=0; i<heights.length; i++)
            preferredHeight += heights[i] + vp;

        // I w końcu zapisujemy sumy w obiekcie Dimension.
        return new Dimension(preferredWidth, preferredHeight);
    }

    /** Rozmieszcza kontener w określonym panelu. To jest układ
     * składający się z wierszy i kolumn; należy określić współrzędne
     * x i y, szerokość i wysokość każdego komponentu.
     * @param parent Kontener, którego elementy rozmieszczamy.
     */
    public void layoutContainer(Container parent) {
        Debug.println("layout","layoutContainer:");
        if (!validWidths)
            return;
        Component[] components = parent.getComponents();
        Dimension contSize = parent.getSize();
        int x = 0;
        for (int i=0; i<components.length; i++) {
            int row = i / COLUMNS;
            int col = i % COLUMNS;
            Component c = components[i];
            Dimension d = c.getPreferredSize();
            int colWidth = (int)(contSize.width * widthPercentages[col]);

            if (col == 0) {
                x = hpad;
            } else {
                x += hpad * (col-1) + 
                    (int)(contSize.width * widthPercentages[col-1]);
            }
            int y = vpad * (row) + (row * heights[row]) + (heights[row]-d.height);
            Rectangle r = new Rectangle(x, y, colWidth, d.height);
            c.setBounds(r);
        }
    }
}
// END main
