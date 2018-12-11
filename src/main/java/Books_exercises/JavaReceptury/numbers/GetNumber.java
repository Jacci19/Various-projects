package Books_exercises.JavaReceptury.numbers;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Program określający czy liczba jest zmiennoprzecinkowa czy całkowita.
 *
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class GetNumber extends Frame {

   /** Pole tekstowe do wpisywania danych */
    private TextField textField;
    /** Pole prezentacji wyniku */
    private TextField statusLabel;

    /** Konstruktor: utworzenie graficznego interfejsu użytkownika */
    public GetNumber() {
        Panel p = new Panel();
        p.add(new Label("Liczba:"));
        p.add(textField = new TextField(10));
        add(BorderLayout.NORTH, p);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String s = textField.getText();
                statusLabel.setText(process(s).toString());
            }
        });
        add(BorderLayout.SOUTH, statusLabel = new TextField(10));
        pack();
    }

    private static Number NAN = new Double(Double.NaN);

    /* Przetwarza jeden łańcuch znaków, zwraca go w formie obiektu
     * klasy potomnej Number. Nie wymaga graficznego interfejsu 
     * użytkownika.
     */
    public static Number process(String s) {
        if (s.matches("[+-]*\\d*\\.\\d+[dDeEfF]*")) {
            try {
                double dValue = Double.parseDouble(s);
                System.out.println("Liczba zmiennoprzecinkowa: " + dValue);
                return Double.valueOf(dValue);
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowa liczba zmiennoprzecinkowa: " + s);
                return NAN;
            }
        } else // łańcuch nie zawierał . d e ani f, próbujmy skonwertować na liczbę int.
            try {
                int iValue = Integer.parseInt(s);
                System.out.println("Liczba całkowita: " + iValue);
                return Integer.valueOf(iValue);
            } catch (NumberFormatException e2) {
                System.out.println("Dane wejściowe nie są liczbą: " + s);
                return NAN;
            }
    }

    public static void main(String[] ap) {
        new GetNumber().setVisible(true);
    }
}
// END main
