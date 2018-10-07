package Books_exercises.HeadFirst_DesPatt.w01_strategy;

public class Duck_Mallard extends Duck {
 
	public Duck_Mallard() {														//kaczka krzyżówka
		quackBehavior = new Quack();
		flyBehavior = new Fly_WithWings();
	}
 
	public void display() {
		System.out.println("I'm a real Mallard duck");
	}
}
