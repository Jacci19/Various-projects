package Books_exercises.JavaReceptury.oo.interfaces;

public class RoomLights extends BuildingLight implements PowerSwitchable {
	RoomLights(int roomNumber) {
		super(roomNumber);
	}
	public void powerDown() {
		System.out.println("Gaszenie świateł w pokoju " + room);
	}
	public void powerUp() {
		System.out.println("Zapalanie świateł w pokoju " + room);
	}
}
