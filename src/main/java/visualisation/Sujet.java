package visualisation;

import comportement.Comportement;
import etats.EtatParticule;
import particules.Particule;

public interface Sujet {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserversRemove();
    void notifyObserversAdd();
    void notifyObserversEtat(Particule p, EtatParticule etat);
    void notifyObserversComportement(Particule p, Comportement comportement);
}
