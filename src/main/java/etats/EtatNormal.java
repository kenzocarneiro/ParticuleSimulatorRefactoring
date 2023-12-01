package etats;

import particules.Particule;

public abstract class EtatNormal extends EtatParticule {
    public EtatNormal(Particule particule) {
        super(particule);
        particule.resetVitesse();
    }

    @Override
    public EtatParticule meurt() {
        return new EtatNormalMorte(this.particule);
    }

    @Override
    public EtatParticule excite() {
        return intervertirEtat();
    }

    @Override
    public EtatType getEtatType() {
        return EtatType.NORMAL;
    }
}
