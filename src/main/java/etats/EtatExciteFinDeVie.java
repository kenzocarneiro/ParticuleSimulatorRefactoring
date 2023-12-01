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
            return FabriqueEtat.getInstance().creationEtat(particule, EtatType.EXCITE, CycleType.MORTE);
        }
        return this;
    }

    @Override
    public EtatParticule intervertirEtat() {
        return FabriqueEtat.getInstance().creationEtat(particule, EtatType.NORMAL, CycleType.FIN_DE_VIE);
    }

    @Override
    public CycleType getCycleType() {
        return CycleType.FIN_DE_VIE;
    }
}
