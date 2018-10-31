package Books_exercises.HeadFirst_DesPatt.w12_combining.combining.observer;

public interface QuackObservable {
	public void registerObserver(Observer observer);
	public void notifyObservers();
}
