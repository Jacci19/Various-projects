package Books_exercises.HeadFirst_DesPatt.w01_strategy;

public class Duck_Decoy extends Duck {
	public Duck_Decoy() {														//kaczka wabik
		setFlyBehavior(new Fly_NoWay());
		setQuackBehavior(new Quack_Mute());
	}
	public void display() {
		System.out.println("I'm a duck Decoy");
	}
}
