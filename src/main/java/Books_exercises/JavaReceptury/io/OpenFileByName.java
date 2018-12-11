package Books_exercises.JavaReceptury.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class OpenFileByName {
	@SuppressWarnings("unused") 
	public static void main(String[] args) throws IOException {
		BufferedReader is = new BufferedReader(new FileReader("myFile.txt"));
		BufferedOutputStream bytesOut = new BufferedOutputStream(
			new FileOutputStream("bytes.dat"));

		// Tu można umieścić kod odczytujący dane z is i zapisujący
		// je w strumieniu bytesOut

		bytesOut.close();
	}
}
