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
    public double getX() {
        return this.particuleDecoree.getX();
    }

    @Override
    public void setX(double x) {
        this.particuleDecoree.setX(x);
    }

    @Override
    public double getY() {
        return this.particuleDecoree.getY();
    }

    @Override
    public void setY(double y) {
        this.particuleDecoree.setY(y);
    }

    @Override
    public void gestionCycle() {
        this.particuleDecoree.gestionCycle();
    }

    @Override
    public void printIt() {
        this.particuleDecoree.printIt();
    }

    @Override
    public void calculeDeplacementAFaire() {
        this.particuleDecoree.calculeDeplacementAFaire();
    }

    @Override
    public void effectueDeplacement() {
        this.particuleDecoree.effectueDeplacement();
    }

    @Override
    public boolean estMorte() {
        return this.particuleDecoree.estMorte();
    }

    @Override
    protected List<Particule> collisionSimpleBilateral(List<Particule> voisins) {
        return this.particuleDecoree.collisionSimpleBilateral(voisins);
    }

    @Override
    public boolean collisionMultiple(List<Particule> c) {
        return this.particuleDecoree.collisionMultiple(c);
    }

    @Override
    protected void augmentationVitesse() {
        this.particuleDecoree.augmentationVitesse();
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

    @Override
    public boolean isVisible() {
        return nbTour % 100 < 50;
    }

}
