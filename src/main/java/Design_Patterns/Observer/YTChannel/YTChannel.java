package Design_Patterns.Observer.YTChannel;

import java.util.ArrayList;

import Design_Patterns.Observer.YTChannel.interfaces.Observer;
import Design_Patterns.Observer.YTChannel.interfaces.Subject;

public class YTChannel implements Subject{

    private ArrayList<Observer> observerList;

    public  YTChannel() {                                       //konstruktor
        observerList = new ArrayList<>();
    }

    public void publishNewVideo(){
        System.out.println("YTChannel: Publisching new video.");
        notifyObservers();
    }

    @Override
    public void register(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observerList){
            o.update();
        }
    }

}
