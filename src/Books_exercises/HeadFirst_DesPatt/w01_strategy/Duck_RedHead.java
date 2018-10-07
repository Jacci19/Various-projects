package Books_exercises.HeadFirst_DesPatt.w01_strategy;

public class Duck_RedHead extends Duck {
 
	public Duck_RedHead() {
		flyBehavior = new Fly_WithWings();
		quackBehavior = new Quack();
	}
 
	public void display() {
		System.out.println("I'm a real Red Headed duck");
	}
}
