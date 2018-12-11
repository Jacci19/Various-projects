package Books_exercises.JavaReceptury.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.darwinsys.swingui.ErrorUtil;

/**
 * ThreadBasedCatcher - Program pokazuje przechwytywanie nieobsłużonych 
 * wyjątków zgłaszanych w innych wątkach.
 * @author Ian Darwin
 */
public class ThreadBasedCatcher extends JFrame{
    private final boolean gui = true;

    // BEGIN main
    // gui/ThreadBasedCatcher.java
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                new ThreadBasedCatcher().setVisible(true);                
            }
        }).start();
    }
    public ThreadBasedCatcher(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        JButton crasher = new JButton("Awaria");
        cp.add(crasher);
        crasher.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                throw new RuntimeException("Sam tego chciałeś!");
            }
        });
        Thread.setDefaultUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler(){
                    public void uncaughtException(Thread t, Throwable ex){
                        if (gui) {
                            ErrorUtil.showExceptions(
                                ThreadBasedCatcher.this, ex);
                        }
                        System.out.println(
                            "Doprowadziłeś do awarii wątku " + t.getName());
                        System.out.println(
                            "Zgłoszono wyjątek: " + ex.toString());
                    }
                });
        pack();
    }
}
// END main
