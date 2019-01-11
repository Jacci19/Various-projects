package JavaFX.Sim;

import JavaFX.Sim.ui.javaFX.JavaFxApplication;

/**
 * *  Nic w tej grze ("Sim") nie modyfikowałem, tylko ściągnąłem z gita, nie warto analizować   - Jacek
 *
 * Entry point for this project. Here, the used UI framework is chosen. The application is started in a new thread. * @author Steven Schwenke
 * https://github.com/stevenschwenke/SimFX			- stąd ściągnąłem
 * https://stevenschwenke.de/javaFXSeriesThreadingIssues    - tu autor coś o niej pisze
 *  Nie wiem o co chodzi w tej grze.....

 */
public class Start {

	public static void main(String[] args) throws InterruptedException {

		// If there were other user interfaces, here would be the decision which
		// of them to start. Right now, there is only JavaFX.

		Thread t = new Thread() {
			@Override
			public void run() {
				System.out.println("Starting JavaFX ...");
				JavaFxApplication.main(new String[0]);
				System.out.println("JavaFX ended");
			}
		};

		t.start();
		t.join();
		System.out.println("terminating or closing java program");
		System.exit(1);
	}

	// TODO use logger instead of System.out

}
