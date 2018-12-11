package Books_exercises.JavaReceptury.json;

import org.json.JSONObject;
import org.json.JSONException;

// BEGIN main
public class WriteOrgJson {
    public static void main(String[] args) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name", "robinParse").        // <1>
            put("Version", "1.2.3").
            put("Class", "RobinParse");
        String printable = jsonObject.toString();    // <2>
        System.out.println(printable);
    }
}
// END main
