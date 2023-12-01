package etats;

import particules.Particule;

public abstract class EtatNormal extends EtatParticule {
    public EtatNormal(Particule particule) {
        super(particule);
        particule.resetVitesse();
    }

    @Override
    public EtatParticule meurt() {
        return FabriqueEtat.getInstance().creationEtat(particule, EtatType.NORMAL, CycleType.MORTE);
    }

    @Override
    public EtatParticule excite() {
        return intervertirEtat();
    }

    @Override
    public EtatParticule calme() {
        return this;
    }

    @Override
    public EtatType getEtatType() {
        return EtatType.NORMAL;
    }
}
