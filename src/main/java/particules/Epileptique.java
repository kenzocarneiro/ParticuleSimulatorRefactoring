package particules;

import etats.EtatParticule;

import java.awt.*;
import java.util.List;

// décorateur Epileptique de Particule
public class Epileptique extends Particule {
    protected Particule particuleDecoree;

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
    public EtatParticule handleCollision(Particule p) {
        return this.particuleDecoree.handleCollision(p);
    }

    @Override
    public void gestionCycle() {
        this.particuleDecoree.gestionCycle();
    }

    @Override
    public ParticuleType getType() {
        return this.particuleDecoree.getType();
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
    public List<Particule> collisionSimpleBilateral(List<Particule> voisins) {
        return this.particuleDecoree.collisionSimpleBilateral(voisins);
    }

    @Override
    public boolean collisionMultiple(List<Particule> c) {
        return this.particuleDecoree.collisionMultiple(c);
    }

    @Override
    public boolean collisionSimple(List<Particule> champ) {
        if(this.isVisible()) {
            return this.particuleDecoree.collisionSimple(champ);
        }
        return false;
    }

    @Override
    public double getVitesseOriginale() {
        return this.particuleDecoree.getVitesseOriginale();
    }

    @Override
    public void resetVitesse() {
        this.particuleDecoree.resetVitesse();
    }

    @Override
    public boolean isVisible() {
        return (this.particuleDecoree.nbTour % 4) < 2;
    }

    @Override
    public Color getCouleur() {
        return this.particuleDecoree.getCouleur();
    }

    public EtatParticule intervertirEtat() {
        return this.particuleDecoree.intervertirEtat();
    }

    public boolean estExciteEtActive() {
        return this.particuleDecoree.estExciteEtActive();
    }

    public int getNbTour() {
        return this.particuleDecoree.getNbTour();
    }

    public int getPassageACTIVE() {
        return this.particuleDecoree.getPassageACTIVE();
    }

    public int getPassageFINDEVIE() {
        return this.particuleDecoree.getPassageFINDEVIE();
    }

    public int getPassageMORT() {
        return this.particuleDecoree.getPassageMORT();
    }

    public static List<Particule> getCollisionsSimplesTraitees() {
        return Particule.getCollisionsSimplesTraitees();
    }

    public double getVitesseCourante() {
        return this.particuleDecoree.getVitesseCourante();
    }

    public double getDirectionCourante() {
        return this.particuleDecoree.getDirectionCourante();
    }

    public double getProchaineVitesse() {
        return this.particuleDecoree.getProchaineVitesse();
    }

    public double getProchaineDirection() {
        return this.particuleDecoree.getProchaineDirection();
    }

    public Champ getChamp() {
        return this.particuleDecoree.getChamp();
    }

    public EtatParticule getEtat() {
        return this.particuleDecoree.getEtat();
    }

    public void setVitesseCourante(double vitesseCourante) {
        this.particuleDecoree.setVitesseCourante(vitesseCourante);
    }

    public void setDirectionCourante(double directionCourante) {
        this.particuleDecoree.setDirectionCourante(directionCourante);
    }

    public void setNbTour(int nbTour) {
        this.particuleDecoree.setNbTour(nbTour);
    }

    public void setProchaineVitesse(double prochaineVitesse) {
        this.particuleDecoree.setProchaineVitesse(prochaineVitesse);
    }

    public void setProchaineDirection(double prochaineDirection) {
        this.particuleDecoree.setProchaineDirection(prochaineDirection);
    }

    public void setPassageACTIVE(int passageACTIVE) {
        this.particuleDecoree.setPassageACTIVE(passageACTIVE);
    }

    public void setPassageFINDEVIE(int passageFINDEVIE) {
        this.particuleDecoree.setPassageFINDEVIE(passageFINDEVIE);
    }

    public void setPassageMORT(int passageMORT) {
        this.particuleDecoree.setPassageMORT(passageMORT);
    }

    public void setChamp(Champ champ) {
        this.particuleDecoree.setChamp(champ);
    }

    public void setEtat(EtatParticule etat) {
        this.particuleDecoree.setEtat(etat);
    }

    public void meurt() {
        this.particuleDecoree.meurt();
    }

    public List<Particule> extraireVoisins(List<Particule> c) {
        return this.particuleDecoree.extraireVoisins(c);
    }

    public void setEnCollision(boolean enCollision) {
        this.particuleDecoree.setEnCollision(enCollision);
    }
}
