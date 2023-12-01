package etats;

import particules.Particule;

public class EtatNormalJeune extends EtatNormal {
    private float coefColor = 1.0f;
    public EtatNormalJeune(Particule particule) {
        super(particule);
    }

    public float getCoefColor() {
        return coefColor;
    }

    @Override
    public EtatParticule gestionCycle(){
        if(particule.getNbTour() == particule.getPassageACTIVE()){
            return FabriqueEtat.getInstance().creationEtat(particule, EtatType.NORMAL, CycleType.ACTIVE);
        }
        return this;
    }

    @Override
    public EtatParticule intervertirEtat() {
        return FabriqueEtat.getInstance().creationEtat(particule, EtatType.EXCITE, CycleType.JEUNE);
    }

    @Override
    public CycleType getCycleType() {
        return CycleType.JEUNE;
    }
}
