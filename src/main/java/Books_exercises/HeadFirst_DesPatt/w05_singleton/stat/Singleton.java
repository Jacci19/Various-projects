package Books_exercises.HeadFirst_DesPatt.w05_singleton.stat;

public class Singleton {
	private static Singleton uniqueInstance = new Singleton();
 
	private Singleton() {}
 
	public static Singleton getInstance() {
		return uniqueInstance;
	}
}
