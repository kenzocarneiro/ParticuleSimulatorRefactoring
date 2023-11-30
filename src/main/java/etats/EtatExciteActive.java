package etats;

import particules.Particule;

public class EtatExciteActive extends EtatExcite {
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
        return new EtatNormalActive(particule);
    }

    @Override
    public boolean estExciteEtActive() {
        return true;
    }
}
