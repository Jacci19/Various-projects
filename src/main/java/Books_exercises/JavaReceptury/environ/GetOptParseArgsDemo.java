package Books_exercises.JavaReceptury.environ;

import java.util.Map;

import com.darwinsys.lang.GetOpt;
import com.darwinsys.lang.GetOptDesc;

public class GetOptParseArgsDemo {
public static void main(String[] args) {
// BEGIN main
GetOptDesc[] options = {
    new GetOptDesc('n', "numeric", false),
    new GetOptDesc('o', "output-file", true),
};
Map<String, String> optionsFound = new GetOpt(options).parseArguments(args);
if (optionsFound.get("n") != null) {
    System.out.println("sortType = NUMERIC;");
}
String outputFile = null;
if ((outputFile = optionsFound.get("o")) != null) {
    System.out.println("Plik wyjściowy to: " + outputFile);
} else {
    System.out.println("Wyniki zapisywane w System.out");
}
// END main
}
}