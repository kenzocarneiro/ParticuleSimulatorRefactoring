package visualisation;

public interface Sujet {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
