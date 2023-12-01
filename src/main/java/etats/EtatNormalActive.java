package etats;

import particules.Particule;

public class EtatNormalActive extends EtatNormal {
    private float coefColor = 0.75f;

    public EtatNormalActive(Particule particule) {
        super(particule);
    }

    public float getCoefColor() {
        return coefColor;
    }

    @Override
    public EtatParticule gestionCycle(){
        if(particule.getNbTour() == particule.getPassageFINDEVIE()){
            return FabriqueEtat.getInstance().creationEtat(particule, EtatType.NORMAL, CycleType.FIN_DE_VIE);
        }
        return this;
    }

    @Override
    public EtatParticule intervertirEtat() {
        return FabriqueEtat.getInstance().creationEtat(particule, EtatType.EXCITE, CycleType.ACTIVE);
    }

    @Override
    public CycleType getCycleType() {
        return CycleType.ACTIVE;
    }
}
