package etats;

import particules.Particule;

public abstract class EtatExcite extends EtatParticule {
    public EtatExcite(Particule particule) {
        super(particule);
    }

    @Override
    public EtatParticule meurt() {
        return new EtatExciteMorte(this.particule);
    }

    @Override
    public EtatParticule excite() {
        particule.setProchaineVitesse(particule.getVitesseCourante());
        return this;
    }
}
