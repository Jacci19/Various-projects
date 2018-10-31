package Books_exercises.HeadFirst_DesPatt.w03_decorator.starbuzz;

public abstract class Beverage {
	String description = "Unknown Beverage";			//beverage - napój
  
	public String getDescription() {
		return description;
	}
 
	public abstract double cost();
}
