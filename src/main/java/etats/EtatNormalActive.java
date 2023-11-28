package etats;

import particules.Particule;

import java.util.List;

public class EtatNormalActive extends EtatParticule {

    public EtatNormalActive(Particule particule) {
        super(particule);
    }

    @Override
    public EtatParticule gestionCycle(){
        if(particule.getNbTour() == particule.getPassageFINDEVIE()){
            return new EtatNormalFinDeVie(particule);
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
            return new EtatNormalActive(particule); // TODO change Normal to Excite

        }
        particule.setEnCollision(false);
        return this;
    }
}
