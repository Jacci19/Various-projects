package Design_Patterns.Observer.YTChannel.interfaces;

public interface Subject {

    void register(Observer o);
    void unregister(Observer o);
    void notifyObservers();

}