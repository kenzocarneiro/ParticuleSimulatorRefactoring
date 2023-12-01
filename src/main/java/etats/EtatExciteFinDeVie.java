package etats;

import particules.Particule;

public class EtatExciteFinDeVie extends EtatExcite {
    private float coefColor = 0.5f;

    public EtatExciteFinDeVie(Particule particule) {
        super(particule);
    }

    public float getCoefColor() {
        return coefColor;
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

    @Override
    public CycleType getCycleType() {
        return CycleType.FIN_DE_VIE;
    }
}
