package Books_exercises.JavaReceptury.oo;

/** Demonstracyjna wersja klasy, która będzie używana jako JavaBean
 * lub jako klasa danych w mechanizmie obsługi serwletów.
 * Dzięki temu, że implementuje ona interfejs Serializable,
 * można ją zapisać na dysku lub przesłać na inny komputer za 
 * pośrednictwem połączenia sieciowego.
 */
// BEGIN main
public class SerializableUser implements java.io.Serializable {
    public String name;
    public String address;
    public String country;
    public String phoneNum;

    // Tutaj można wstawić inne pola i metody ...
    static final long serialVersionUID = -7978489268769667877L;
}
// END main
