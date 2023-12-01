package etats;

import particules.Particule;

public class EtatNormalFinDeVie extends EtatNormal {
    private float coefColor = 0.5f;

    public EtatNormalFinDeVie(Particule particule) {
        super(particule);
    }

    public float getCoefColor() {
        return coefColor;
    }

    @Override
    public EtatParticule gestionCycle(){
        if(particule.getNbTour() == particule.getPassageMORT()){
            return FabriqueEtat.getInstance().creationEtat(particule, EtatType.NORMAL, CycleType.MORTE);
        }
        return this;
    }

    @Override
    public EtatParticule intervertirEtat() {
        return FabriqueEtat.getInstance().creationEtat(particule, EtatType.EXCITE, CycleType.FIN_DE_VIE);
    }

    @Override
    public CycleType getCycleType() {
        return CycleType.FIN_DE_VIE;
    }
}
