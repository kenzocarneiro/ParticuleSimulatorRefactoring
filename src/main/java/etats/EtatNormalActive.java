package etats;

import particules.Particule;

import java.util.List;

public class EtatNormalActive extends EtatNormal {

    public EtatNormalActive(Particule particule) {
        super(particule);
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
        particule.augmentationVitesse();
        return new EtatExciteActive(particule);
    }
}
