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

    public EtatParticule collisionSimple(List<Particule> c) {
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
            if (p.isActiveAndExcited() && this.isActiveAndExcited()) {
                particule.handleCollision(p);
            } else {
                p.setEtat(p.intervertirEtat());
                return p.intervertirEtat();
            }
        }
        return this;
    }

    public EtatParticule collisionMultiple(List<Particule> champ) {
        List<Particule> voisins = particule.extraireVoisins(champ);
        if (voisins.size() > 1) {

            if (particule.getDirectionCourante() > Math.PI) particule.setProchaineDirection(Math.PI - particule.getDirectionCourante());
            else particule.setProchaineDirection(Math.PI + particule.getDirectionCourante());

            particule.setProchaineVitesse(particule.getVitesseCourante());
            particule.setEnCollision(true);
            return this;
        }
        particule.setEnCollision(false);
        return this;
    }

    public EtatParticule intervertirEtat() {
        throw new RuntimeException("Operation Impossible");
    }

    public boolean isActiveAndExcited() {
        return false;
    }

    public EtatParticule gestionCycle() {
        //        if (this.nbTour == this.passageACTIVE) {
//            this.phaseDeLaParticule = Phase.ACTIVE;
//        }
//
//        if (this.nbTour == this.passageFINDEVIE) {
//            this.phaseDeLaParticule = Phase.FINDEVIE;
//        }
//
//
//        if (this.nbTour == this.passageMORT) {
//            this.phaseDeLaParticule = Phase.MORTE;
//        }
        throw new RuntimeException("Operation Impossible");
    }
}
