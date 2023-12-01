package comportement;

import etats.EtatExcite;
import particules.Particule;

public class ComportementEpileptique extends Comportement {
    public ComportementEpileptique(Particule particule) {
        super(particule);
    }

    public boolean isVisible() {
        return (this.particule.getNbTour() % 4) < 2;
    }

    public void contamine(Particule p) {
        if(this.particule.estExciteEtActive() && p.getEtat() instanceof EtatExcite) {
            p.setComportement(new ComportementEpileptique(p));
        }
    }

    public ComportementType getComportementType() {
        return ComportementType.EPILEPTIQUE;
    }
}
