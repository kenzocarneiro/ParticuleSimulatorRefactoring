package etats;

import particules.Particule;

import java.util.List;

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
        particule.augmentationVitesse();
        return new EtatExciteFinDeVie(particule);
    }
}
