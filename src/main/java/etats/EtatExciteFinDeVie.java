package etats;

import particules.Particule;

public class EtatExciteFinDeVie extends EtatParticule {
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
        particule.resetVitesse();
        return new EtatNormalFinDeVie(particule);
    }
}
