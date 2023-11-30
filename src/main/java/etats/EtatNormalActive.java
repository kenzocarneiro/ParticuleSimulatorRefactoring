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
            return new EtatNormalFinDeVie(particule);
        }
        return this;
    }

    @Override
    public EtatParticule intervertirEtat() {
        return new EtatExciteActive(particule);
    }
}
