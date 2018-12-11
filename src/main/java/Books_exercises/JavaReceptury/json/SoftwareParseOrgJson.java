package Books_exercises.JavaReceptury.json;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

// BEGIN main
public class SoftwareParseOrgJson {
    final static String FILE_NAME = "/json/softwareinfo.json";

    public static void main(String[] args) throws Exception {
        
        InputStream jsonInput =
            SoftwareParseOrgJson.class.getResourceAsStream(FILE_NAME);
        if (jsonInput == null) {
            throw new NullPointerException("Nie mogę znaleźć pliku" + FILE_NAME);
        }
        JSONObject obj = new JSONObject(new JSONTokener(jsonInput));      // <1>
        System.out.println("Nazwa programu: " + obj.getString("name"));    // <2>
        System.out.println("Numer wersji: " + obj.getString("version"));
        System.out.println("Opis: " + obj.getString("description"));
        System.out.println("Klasa: " + obj.getString("className"));
        JSONArray contribs = obj.getJSONArray("contributors");            // <3>
        for (int i = 0; i < contribs.length(); i++) {                     // <4>
            System.out.println("Personalia współpracownika: " + contribs.get(i));
        }
    }

}
// END main
