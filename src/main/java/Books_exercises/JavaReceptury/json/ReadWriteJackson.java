package Books_exercises.JavaReceptury.json;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Person;

// BEGIN main
public class ReadWriteJackson {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();                // <1>
        
        String jsonInput =                                       // <2>
                "{\"id\":0,\"firstName\":\"Robin\",\"lastName\":\"Wilson\"}";
        Person q = mapper.readValue(jsonInput, Person.class);
        System.out.println("Odczyt i przetworzenie obiektu Person z formatu JSON: " + q);
        
        Person p = new Person("Roger", "Rabbit");                // <3>
        System.out.print("Obiekt Person " + p +" zapisany w formacie JSON = ");
        mapper.writeValue(System.out, p);
    }
}
// END main
    