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
        return FabriqueEtat.getInstance().creationEtat(particule, EtatType.EXCITE, CycleType.MORTE);
    }

    @Override
    public EtatParticule excite() {
        return this;
    }

    @Override
    public EtatParticule calme() {
        return intervertirEtat();
    }

    @Override
    public EtatType getEtatType() {
        return EtatType.EXCITE;
    }
}
