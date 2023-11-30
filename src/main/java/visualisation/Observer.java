package visualisation;

import comportement.Comportement;
import etats.EtatParticule;
import particules.Particule;

import java.util.List;

public interface Observer {
    void updateRemove(List<Particule> p);
    void updateAdd(List<Particule> p);
    void updateEtat(EtatParticule oldEtat, EtatParticule newEtat);
    void updateComportement(Comportement oldComportement, Comportement newComportement);
}
