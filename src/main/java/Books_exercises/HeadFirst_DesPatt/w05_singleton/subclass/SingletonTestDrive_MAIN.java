package Books_exercises.HeadFirst_DesPatt.w05_singleton.subclass;

public class SingletonTestDrive_MAIN {
	public static void main(String[] args) {
		Singleton foo = CoolerSingleton.getInstance();
		Singleton bar = HotterSingleton.getInstance();
		System.out.println(foo);
		System.out.println(bar);
 	}
}
