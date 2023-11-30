package etats;

import particules.Particule;

import java.util.List;

public abstract class EtatParticule {
    Particule particule;
    public EtatParticule(Particule particule) {
        this.particule = particule;
    }

    public Particule getParticule() {
        return particule;
    }

    public EtatParticule collisionSimple(List<Particule> champ) {
        List<Particule> enCollisionFrontale = particule.collisionSimpleBilateral(particule.getChamp().getParticules());

        if (enCollisionFrontale.size() != 1) {
            particule.setEnCollision(false);
            return this;
        }
        particule.setEnCollision(true);

        Particule.collisionsSimplesTraitees.add(particule);
        if (particule.getDirectionCourante() > Math.PI) {
            particule.setProchaineDirection(particule.getDirectionCourante() - Math.PI);
        } else {
            particule.setProchaineDirection(particule.getDirectionCourante() + Math.PI);
        }

        for (Particule p : enCollisionFrontale) {
            if (p.getDirectionCourante() > Math.PI) p.setProchaineDirection(p.getDirectionCourante() - Math.PI);
            else p.setProchaineDirection(p.getDirectionCourante() + Math.PI);
            Particule.collisionsSimplesTraitees.add(p);
            if (p.isActiveAndExcited() && this.estExciteEtActive()) {
                particule.handleCollision(p);
            } else {
                p.setEtat(p.intervertirEtat());
                return p.intervertirEtat();
            }
        }
        return this;
    }

    public abstract EtatParticule collisionMultiple(List<Particule> champ);

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
}
