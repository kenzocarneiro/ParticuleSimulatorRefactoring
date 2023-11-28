package etats;

import particules.Particule;
import particules.ParticuleA;
import particules.ParticuleB;

import java.util.List;

public class EtatNormalJeune extends EtatParticule {

    public EtatNormalJeune(Particule particule) {
        super(particule);
    }

    @Override
    public EtatParticule gestionCycle(){
        if(particule.getNbTour() == particule.getPassageACTIVE()){
            return new EtatNormalActive(particule);
        }
        return this;
    }
    @Override
    public EtatParticule collisionMultiple(List<Particule> champ) {
        List<Particule> voisins = particule.extraireVoisins(champ);

        if (voisins.size() > 1) {

            if (particule.getDirectionCourante() > Math.PI)
                particule.setProchaineDirection(Math.PI - particule.getDirectionCourante());
            else particule.setProchaineDirection(Math.PI + particule.getDirectionCourante());

            particule.augmentationVitesse();
            particule.setEnCollision(true);
            return new EtatNormalJeune(particule); // TODO change Normal to Excite

        }
        particule.setEnCollision(false);
        return this;
    }

}
