package Books_exercises.JavaReceptury.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import com.darwinsys.swingui.LabelText;

// BEGIN main
/** Prezentacja wykorzystania przycisku. */
public class LabelTextDemo extends JFrame {

    public LabelTextDemo() {
        setLayout(new FlowLayout(FlowLayout.LEADING));
        add(new LabelText("Test: "));
        add(new LabelText("Test 2: "));
        add(new LabelText("Test 3: "));
        setSize(200, 120);
    }
    
    public static void main(String[] unuxed) {
        new LabelTextDemo().setVisible(true);
    }
}
// END main
