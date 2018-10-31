package Books_exercises.HeadFirst_DesPatt.w01_strategy;

public abstract class Duck {
	Fly_Behavior flyBehavior;
	Quack_Behavior quackBehavior;
 
	public Duck() {
	}
 
	public void setFlyBehavior (Fly_Behavior fb) {
		flyBehavior = fb;
	}
 
	public void setQuackBehavior(Quack_Behavior qb) {
		quackBehavior = qb;
	}
 
	abstract void display();
 
	public void performFly() {
		flyBehavior.fly();
	}
 
	public void performQuack() {
		quackBehavior.quack();
	}
 
	public void swim() {
		System.out.println("All ducks float, even decoys!");
	}
}
