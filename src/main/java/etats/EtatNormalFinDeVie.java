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
            return new EtatNormalMorte(particule);
        }
        return this;
    }

    @Override
    public EtatParticule intervertirEtat() {
        return new EtatExciteFinDeVie(particule);
    }
}
