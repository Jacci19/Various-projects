package Books_exercises.JavaReceptury.oo.interfaces;

/**
 * BuildingManagement - zarządzanie budynkiem oszczędzającym energię.
 * Ta klasa pokazuje, w jaki sposób można zarządzać urządzeniami 
 * w biurze, które z nich można bezpiecznie wyłączyć na noc w celu 
 * zaoszczędzenia energii elektrycznej - znacznych ilości energii 
 * w przypadku dużych biur.
 */
// BEGIN main
public class BuildingManagement {

    Asset things[] = new Asset[24];
    int numItems = 0;

    /** Scenariusz: goodNight wywoływana przez wątek czasomierza o godzinie
     * 22:00 lub wówczas, gdy system otrzyma polecenie "shutdown" od 
     * strażnika.
     */
    public void goodNight() {
        for (int i=0; i<things.length; i++)
            if (things[i] instanceof PowerSwitchable)
                ((PowerSwitchable)things[i]).powerDown();
    }

    // Metoda goodMorning() działałaby bardzo podobnie, z tą 
    // różnica, że wywoływałaby metodę powerUp().

    /** Metoda dodaje obiekt Asset do tego budynku. */
    public void add(Asset thing) {
        System.out.println("Dodajemy " + thing);
        things[numItems++] = thing;
    }

    /** Program główny. */
    public static void main(String[] av) {
        BuildingManagement b1 = new BuildingManagement();
        b1.add(new RoomLights(101));    // Oświetlenie w pokoju 101.
        b1.add(new EmergencyLight(101));    // Oświetlenie awaryjne.
        // Dodaj komputer na biurku nr 4 w pokoju 101.
        b1.add(new ComputerCPU(10104));
        // Dodaj monitor tego komputera.
        b1.add(new ComputerMonitor(10104));

        // Czas mija... słońce zachodzi...
        b1.goodNight();
    }
}
// END main
