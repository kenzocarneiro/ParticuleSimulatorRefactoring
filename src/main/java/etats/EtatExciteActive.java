package etats;

import particules.Particule;

import java.util.List;

public class EtatExciteActive extends EtatParticule {
    public EtatExciteActive(Particule particule) {
        super(particule);
    }

    @Override
    public EtatParticule gestionCycle() {
        if (particule.getNbTour() == particule.getPassageFINDEVIE()) {
            return new EtatExciteFinDeVie(particule);
        }
        return this;
    }

    @Override
    public EtatParticule intervertirEtat() {
        particule.resetVitesse();
        return new EtatExciteActive(particule); // TODO: passer en Normal
    }

    @Override
    public boolean isActiveAndExcited() {
        return true;
    }
}
