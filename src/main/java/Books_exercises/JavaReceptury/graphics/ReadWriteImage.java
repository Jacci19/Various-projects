package Books_exercises.JavaReceptury.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Program wczytuje obrazek, dodaje do niego tekst i ponownie zapisuje 
 * obrazek w pliku. 
 * W rzeczywistości zapisuje nawet całą grupę obrazków, których użyłem 
 * w tej książce jako ikon oznaczających wymaganą wersję języka Java.
 * @author Ian Darwin
 */
public class ReadWriteImage {
	final static int VERSIONS[] = { 5,6,7,8};
	final static String DIRECTORY = "images" + File.separatorChar;

	public static void main(String[] args) throws Exception {
		String dir = DIRECTORY;
		for (int v : VERSIONS) {
			BufferedImage image = ImageIO.read(new File(dir + "coffeecup.png"));
			Graphics2D g = image.createGraphics();
			Font f = new Font("Serif", Font.BOLD, 160);
			g.setFont(f);
			g.setColor(Color.black);
			String bigNumberLabel = Integer.toString(v);
			Rectangle2D lineMetrics = f.getStringBounds(bigNumberLabel, g.getFontRenderContext() );
			int x = (int) ((image.getWidth() - lineMetrics.getWidth() ) / 2);
			x -= 10;								// przesunięcie
			int y = (int) ((image.getHeight() + lineMetrics.getHeight()) / 2);
			g.drawString(bigNumberLabel, x, y);
			ImageIO.write(image, "png", new File(String.format("%sjava%d.png",dir, v)));
		}
		System.exit(0);	// Kod graficzny uruchamia dodatkowy wątek... 
	}
}
