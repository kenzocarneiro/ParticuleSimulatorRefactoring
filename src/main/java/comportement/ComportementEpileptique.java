package comportement;

import particules.Particule;

public class ComportementEpileptique extends Comportement {
    public ComportementEpileptique(Particule particule) {
        super(particule);
    }

    public boolean isVisible() {
        return (this.particule.getNbTour() % 4) < 2;
    }
}
