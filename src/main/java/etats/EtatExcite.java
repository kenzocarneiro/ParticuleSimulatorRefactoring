package etats;

import particules.Particule;

public abstract class EtatExcite extends EtatParticule {
    public EtatExcite(Particule particule) {
        super(particule);
        particule.setProchaineVitesse(particule.getVitesseExcite());
        particule.setVitesseCourante(particule.getProchaineVitesse());
    }

    @Override
    public EtatParticule meurt() {
        return new EtatExciteMorte(this.particule);
    }

    @Override
    public EtatParticule excite() {
        return this;
    }

    @Override
    public EtatType getEtatType() {
        return EtatType.EXCITE;
    }
}
