package etats;

import particules.Particule;

import java.util.List;


public class EtatNormalMorte extends EtatNormal {
    public EtatNormalMorte(Particule particule) {
        super(particule);
    }

    @Override
    public EtatParticule intervertirEtat() {
        particule.augmentationVitesse();
        return new EtatExciteMorte(particule);
    }
}
