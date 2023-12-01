package etats;

import particules.Particule;

import java.util.List;

public abstract class EtatParticule {
    protected Particule particule;
    public EtatParticule(Particule particule) {
        this.particule = particule;
    }

    public Particule getParticule() {
        return particule;
    }

    public abstract float getCoefColor();

    public EtatParticule collisionSimple(List<Particule> champ) {
        if(!particule.isVisible()) {
            particule.setEnCollision(false);
            return this;
        }

        List<Particule> enCollisionFrontale = particule.collisionSimpleBilateral(particule.getChamp().getParticules());

        if (enCollisionFrontale.size() != 1) {
            particule.setEnCollision(false);
            return this;
        }
        particule.setEnCollision(true);
        Particule p = enCollisionFrontale.get(0);

        if(!p.isVisible()) {
            return this;
        }

        Particule.collisionsSimplesTraitees.add(particule);
        if (particule.getDirectionCourante() > Math.PI) {
            particule.setProchaineDirection(particule.getDirectionCourante() - Math.PI);
        } else {
            particule.setProchaineDirection(particule.getDirectionCourante() + Math.PI);
        }
        if (p.getDirectionCourante() > Math.PI) {
            p.setProchaineDirection(p.getDirectionCourante() - Math.PI);
        } else {
            p.setProchaineDirection(p.getDirectionCourante() + Math.PI);
        }

        particule.contamine(p);
        p.contamine(particule);

        Particule.collisionsSimplesTraitees.add(p);
        if (p.estExciteEtActive() && this.estExciteEtActive()) {
            return particule.handleCollision(p);
        } else {
            p.setEtat(p.intervertirEtat());
            return particule.intervertirEtat();
        }
    }

    public EtatParticule collisionMultiple(List<Particule> champ) {
        if(!particule.isVisible()) {
            particule.setEnCollision(false);
            return this;
        }
        List<Particule> voisins = particule.extraireVoisins(champ);
        if (voisins.size() > 1) {

            if (particule.getDirectionCourante() > Math.PI)
                particule.setProchaineDirection(Math.PI - particule.getDirectionCourante());
            else particule.setProchaineDirection(Math.PI + particule.getDirectionCourante());

            particule.setEnCollision(true);
            return excite();

        }
        particule.setEnCollision(false);
        return this;
    }

    public EtatParticule intervertirEtat() {
        throw new RuntimeException("Operation Impossible");
    }

    public boolean estExciteEtActive() {
        return false;
    }

    public boolean estMorte() {
        return false;
    }

    public EtatParticule gestionCycle() {
        return this;
    }

    public abstract EtatParticule meurt();

    public abstract EtatParticule excite();

    public abstract EtatType getEtatType();
    public abstract CycleType getCycleType();
}
