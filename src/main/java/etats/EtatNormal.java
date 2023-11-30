package etats;

import particules.Particule;

import java.util.List;

public abstract class EtatNormal extends EtatParticule {
    public EtatNormal(Particule particule) {
        super(particule);
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
            return new EtatExciteFinDeVie(particule);

        }
        particule.setEnCollision(false);
        return this;
    }

    @Override
    public EtatParticule meurt() {
        return new EtatExciteMorte(this.particule);
    }
}
