package visualisation;

import etats.EtatParticule;
import particules.Particule;

import java.util.List;

public interface Observer {
    void updateRemove(List<Particule> p);
    void updateAdd(List<Particule> p);
    void updateEtat(EtatParticule oldEtat, EtatParticule newEtat);
}
