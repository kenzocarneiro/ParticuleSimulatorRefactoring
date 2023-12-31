package etats;

import particules.Particule;

public class EtatNormalMorte extends EtatNormal {
    private float coefColor = 0.25f;

    public EtatNormalMorte(Particule particule) {
        super(particule);
    }

    public float getCoefColor() {
        return coefColor;
    }

    @Override
    public EtatParticule intervertirEtat() {
        return FabriqueEtat.getInstance().creationEtat(particule, EtatType.EXCITE, CycleType.MORTE);
    }

    @Override
    public boolean estMorte() {
        return true;
    }

    @Override
    public CycleType getCycleType() {
        return CycleType.MORTE;
    }
}
