package etats;

import particules.Particule;

public class EtatExciteJeune extends EtatParticule {
    public EtatExciteJeune(Particule particule) {
        super(particule);
    }

    @Override
    public EtatParticule gestionCycle() {
        if (particule.getNbTour() == particule.getPassageACTIVE()) {
            return new EtatExciteActive(particule);
        }
        return this;
    }

    @Override
    public EtatParticule intervertirEtat() {
        particule.resetVitesse();
        return new EtatNormalJeune(particule);
    }
}
