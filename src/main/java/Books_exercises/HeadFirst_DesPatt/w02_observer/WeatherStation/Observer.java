package Books_exercises.HeadFirst_DesPatt.w02_observer.WeatherStation;

public interface Observer {
	public void update(float temp, float humidity, float pressure);						//humidity - wilgotność
}
