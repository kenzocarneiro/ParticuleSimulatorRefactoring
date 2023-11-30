package etats;

import particules.Particule;

public class EtatExciteJeune extends EtatExcite {
    private float coefColor = 1.0f;

    public EtatExciteJeune(Particule particule) {
        super(particule);
    }

    public float getCoefColor() {
        return coefColor;
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
        return new EtatNormalJeune(particule);
    }
}
