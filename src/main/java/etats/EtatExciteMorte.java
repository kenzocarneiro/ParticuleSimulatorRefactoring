package etats;

import particules.Particule;

public class EtatExciteMorte extends EtatExcite {
    public EtatExciteMorte(Particule particule) {
        super(particule);
    }

    @Override
    public EtatParticule intervertirEtat() {
        return new EtatNormalMorte(particule);
    }

    @Override
    public boolean estMorte() {
        return true;
    }
}
