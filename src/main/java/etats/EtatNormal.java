package etats;

import particules.Particule;

public abstract class EtatNormal extends EtatParticule {
    public EtatNormal(Particule particule) {
        super(particule);
    }

    @Override
    public EtatParticule meurt() {
        return new EtatNormalMorte(this.particule);
    }

    @Override
    public EtatParticule excite() {
        return intervertirEtat();
    }
}
