package etats;

import particules.Particule;

public class EtatExciteFinDeVie extends EtatExcite {
    public EtatExciteFinDeVie(Particule particule) {
        super(particule);
    }

    @Override
    public EtatParticule gestionCycle() {
        if (particule.getNbTour() == particule.getPassageMORT()) {
            return new EtatExciteMorte(particule);
        }
        return this;
    }

    @Override
    public EtatParticule intervertirEtat() {
        return new EtatNormalFinDeVie(particule);
    }
}
