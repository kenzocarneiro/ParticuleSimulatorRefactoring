package etats;

import particules.Particule;

public class EtatExciteActive extends EtatExcite {
    private float coefColor = 0.75f;

    public EtatExciteActive(Particule particule) {
        super(particule);
    }

    public float getCoefColor() {
        return coefColor;
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
        return new EtatNormalActive(particule);
    }

    @Override
    public boolean estExciteEtActive() {
        return true;
    }

    @Override
    public CycleType getCycleType() {
        return CycleType.ACTIVE;
    }
}
