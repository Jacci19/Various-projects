package Books_exercises.HeadFirst_DesPatt.w01_strategy;

public class Duck_Rubber extends Duck {
 
	public Duck_Rubber() {
		flyBehavior = new Fly_NoWay();
		quackBehavior = new Quack_Squeak();
	}
 
	public void display() {
		System.out.println("I'm a rubber duckie");
	}
}
