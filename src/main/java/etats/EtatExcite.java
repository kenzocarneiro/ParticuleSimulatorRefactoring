package etats;

import particules.Particule;

import java.util.List;

public abstract class EtatExcite extends EtatParticule {
    public EtatExcite(Particule particule) {
        super(particule);
    }

    @Override
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
}
