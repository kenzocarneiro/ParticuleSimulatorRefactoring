package etats;

import particules.Particule;

public class EtatNormalFinDeVie extends EtatNormal {

    public EtatNormalFinDeVie(Particule particule) {
        super(particule);
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
