package Books_exercises.HeadFirst_DesPatt.w10_state.gumballstate;

public interface State {
 
	public void insertQuarter();
	public void ejectQuarter();
	public void turnCrank();
	public void dispense();
}
