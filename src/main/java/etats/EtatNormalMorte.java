package etats;

import particules.Particule;

public class EtatNormalMorte extends EtatNormal {
    public EtatNormalMorte(Particule particule) {
        super(particule);
    }

    @Override
    public EtatParticule intervertirEtat() {
        return new EtatExciteMorte(particule);
    }

    @Override
    public boolean estMorte() {
        return true;
    }
}
