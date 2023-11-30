package etats;

import particules.Particule;
import particules.ParticuleA;
import particules.ParticuleB;

import java.util.List;

public class EtatNormalJeune extends EtatNormal {

    public EtatNormalJeune(Particule particule) {
        super(particule);
    }

    @Override
    public EtatParticule gestionCycle(){
        if(particule.getNbTour() == particule.getPassageACTIVE()){
            return new EtatNormalActive(particule);
        }
        return this;
    }

    @Override
    public EtatParticule intervertirEtat() {
        particule.augmentationVitesse();
        return new EtatExciteJeune(particule);
    }
}
