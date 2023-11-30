package etats;

import particules.Particule;

import java.util.List;

public class EtatExciteMorte extends EtatParticule {
    public EtatExciteMorte(Particule particule) {
        super(particule);
    }

    @Override
    public EtatParticule intervertirEtat() {
        particule.resetVitesse();
        return new EtatNormalMorte(particule);
    }
}
