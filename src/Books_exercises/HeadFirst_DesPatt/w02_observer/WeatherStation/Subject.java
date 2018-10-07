package Books_exercises.HeadFirst_DesPatt.w02_observer.WeatherStation;

public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
