package Books_exercises.JavaReceptury.redemo;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

/* Program podobny do REDemo, lecz pokazuje grupy w wielowierszowym polu
 * tekstowym.
 */
public class REDemo2 extends REDemo {

    JTextArea logTextArea;
    
    /** Główna metoda programu - tworzy interfejs użytkownika i wyświetla go. */
    public static void main(String[] av) throws BadLocationException {
        JFrame f = new JFrame("REDemo2");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        REDemo2 comp = new REDemo2(f);
        Container cp = f.getContentPane();
        cp.add(comp, BorderLayout.NORTH);
        cp.add(comp.logTextArea, BorderLayout.SOUTH);
        f.pack();
        f.setVisible(true);
    }

    REDemo2(final JFrame parent) throws BadLocationException {
        super(parent);
        logTextArea = new JTextArea(10,40);
        add(logTextArea);
    }

    protected boolean tryMatch() {
        if (pattern == null) {
            return false;
        }
        logTextArea.setText("");

        setMatches(false);
        setHighlightFromMatcher(null);

        matcher.reset(stringTF.getText());
        if (match.isSelected() && matcher.matches()) {
            setMatches(true);
            if (1 <= matcher.groupCount()) {
                setMatches(matcher.groupCount());
            }
            logTextArea.setText("");
            for (int i = 0; i <= matcher.groupCount(); i++) {
                logTextArea.append(i + " " + matcher.group(i) + "\n");
            }
        } else if (findButton.isSelected() && matcher.find()) {
            setMatches(true);
            logTextArea.setText(matcher.group());
        } else if (findAll.isSelected()) {
            int i = 0;
            while (matcher.find()) {
                logTextArea.append(i++ + ": " + matcher.group() + "\n");
            }
            if (i > 0) {
                setMatches(true);
                return true;
            }
        }

        return isMatch();
    }
   
}