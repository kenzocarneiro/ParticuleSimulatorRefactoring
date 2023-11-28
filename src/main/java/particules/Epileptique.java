package particules;

import java.util.List;

// décorateur Epileptique de Particule
public class Epileptique extends Particule {
    protected Particule particuleDecoree;
    private int visible;
    public Epileptique(Particule particule) {
        this.particuleDecoree = particule;
    }

    public Particule getParticuleDecoree() {
        return this.particuleDecoree;
    }


    @Override
    public boolean isVisible() {
        return nbTour % 4 == 0 || nbTour % 4 == 1;
    }

    @Override
    public boolean collisionSimple(List<Particule> champ) {
        if(this.isVisible()) {
            return this.particuleDecoree.collisionSimple(champ);
        }
        return false;
    }

    @Override
    public void resetVitesse() {
        this.particuleDecoree.resetVitesse();
    }
}
