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

    public EtatParticule collisionSimple(List<Particule> champ){
        throw new RuntimeException("Operation Impossible");
    }

    public EtatParticule collisionMultiple(List<Particule> champ) {
//        List<Particule> voisins = this.extraireVoisins(c);
//        if (voisins.size() > 1) {
//
//            if (this.directionCourante > Math.PI) this.prochaineDirection = Math.PI - this.directionCourante;
//            else this.prochaineDirection = Math.PI + this.directionCourante;
//
//
//            if (!(this.etatDeLaParticule == Particule.Etat.EXCITE)) {
//                this.etatDeLaParticule = Particule.Etat.EXCITE;
//                this.augmentationVitesse();
//            } else {
//                this.prochaineVitesse = this.vitesseCourante;
//            }
//            return true;
//        }
//        return false;
        throw new RuntimeException("Operation Impossible");
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
