package etats;

import particules.Particule;

public class EtatExciteMorte extends EtatExcite {
    private float coefColor = 0.25f;

    public EtatExciteMorte(Particule particule) {
        super(particule);
    }

    public float getCoefColor() {
        return coefColor;
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
