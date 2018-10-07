package Books_exercises.HeadFirst_DesPatt.w01_strategy;

public class Duck_Model extends Duck {
	public Duck_Model() {
		flyBehavior = new Fly_NoWay();
		quackBehavior = new Quack();
	}

	public void display() {
		System.out.println("I'm a model duck");
	}
}
