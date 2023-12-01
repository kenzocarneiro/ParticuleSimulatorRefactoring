package particules;

import comportement.Comportement;
import comportement.ComportementEpileptique;
import comportement.ComportementNormal;
import etats.EtatNormalJeune;
import etats.EtatParticule;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public abstract class Particule {

    private static final int epaisseur = 10;
    /**
     * cette variable stocke doit etre reinitialiser a chaque tour de simulation. Elle stocke
     * les collisions simples traitees. Si a est en collision simple avec b (et reciproquement), a est stockee dans
     * cet ensemble. Ainsi au traitement de b, quand on verra que dans son voisinage direct, il y a a, on ne fera
     * pas de nouveau le traitement de la collision.
     */
    public static List<Particule> collisionsSimplesTraitees = new ArrayList<>();
    /**
     * Position a l'ecran en x et y
     */
    protected double x, y;

    /**
     * Vitesse courante en pixels/s
     */
    protected double vitesseCourante;

    /**
     * Direction exprimee en angle (en radians [0 : 2*PI[
     */
    protected double directionCourante;

    protected int nbTour = 0;
    protected double prochaineVitesse;
    protected double prochaineDirection;  // en radians [0 - 2 PI[

    protected int passageACTIVE;
    protected int passageFINDEVIE;
    protected int passageMORT;

    protected Champ champ;
    protected EtatParticule etat;

    protected boolean enCollision;

    protected Comportement comportement;

    public Particule(Champ c, double x, double y, double dC, boolean epileptique) {
        this.champ = c;
        this.x = x;
        this.y = y;
        this.directionCourante = dC;
        this.prochaineDirection = dC;
        this.etat = new EtatNormalJeune(this);
        this.enCollision = false;
        this.comportement = epileptique ? new ComportementEpileptique(this) : new ComportementNormal(this);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Comportement getComportement() {
        return comportement;
    }

    public void setComportement(Comportement comportement) {
        this.champ.notifyObserversComportement(this, comportement);
        this.comportement = comportement;
    }

    public boolean isVisible() {
        return getComportement().isVisible();
    }

    public void contamine(Particule p) {
        getComportement().contamine(p);
    }

    public abstract EtatParticule handleCollision(Particule p);

    public EtatParticule intervertirEtat() {
        return etat.intervertirEtat();
    }

    public boolean estExciteEtActive() {
        return etat.estExciteEtActive();
    }

    public int getNbTour() {
        return nbTour;
    }

    public int getPassageACTIVE() {
        return passageACTIVE;
    }

    public int getPassageFINDEVIE() {
        return passageFINDEVIE;
    }

    public int getPassageMORT() {
        return passageMORT;
    }

    public static List<Particule> getCollisionsSimplesTraitees() {
        return collisionsSimplesTraitees;
    }

    public double getVitesseCourante() {
        return vitesseCourante;
    }

    public double getDirectionCourante() {
        return directionCourante;
    }

    public double getProchaineVitesse() {
        return prochaineVitesse;
    }

    public double getProchaineDirection() {
        return prochaineDirection;
    }

    public Champ getChamp() {
        return champ;
    }

    public EtatParticule getEtat() {
        return etat;
    }

    public static void setCollisionsSimplesTraitees(List<Particule> collisionsSimplesTraitees) {
        Particule.collisionsSimplesTraitees = collisionsSimplesTraitees;
    }

    public void setVitesseCourante(double vitesseCourante) {
        this.vitesseCourante = vitesseCourante;
    }

    public void setDirectionCourante(double directionCourante) {
        this.directionCourante = directionCourante;
    }

    public void setNbTour(int nbTour) {
        this.nbTour = nbTour;
    }

    public void setProchaineVitesse(double prochaineVitesse) {
        this.prochaineVitesse = prochaineVitesse;
    }

    public void setProchaineDirection(double prochaineDirection) {
        this.prochaineDirection = prochaineDirection;
    }

    public void setPassageACTIVE(int passageACTIVE) {
        this.passageACTIVE = passageACTIVE;
    }

    public void setPassageFINDEVIE(int passageFINDEVIE) {
        this.passageFINDEVIE = passageFINDEVIE;
    }

    public void setPassageMORT(int passageMORT) {
        this.passageMORT = passageMORT;
    }

    public void setChamp(Champ champ) {
        this.champ = champ;
    }

    public void setEtat(EtatParticule etat) {
        this.champ.notifyObserversEtat(this, etat);
        this.etat = etat;
    }

    /**
     * Methode appelee a chaque fois qu'un deplacement est fait
     */
    public void gestionCycle() {
        setEtat(etat.gestionCycle());
    }

    public void setEnCollision(boolean enCollision) {
        this.enCollision = enCollision;
    }

    public abstract ParticuleType getType();

    public static int getEpaisseur() {
        return epaisseur;
    }

    /**
     * Calcul de la prochaine direction et vitesse. Dans le cas normal, aucun changement concernant les
     * deux champs. Dans le cas d'une collision simple ou multiples, la maj des champs est deleguee aux
     * methodes associees.
     * // @param champ : liste des particules presentes dans le champ de particules.
     */
    final public void calculeDeplacementAFaire() {
        nbTour++;
        this.gestionCycle();
        if (!Particule.collisionsSimplesTraitees.contains(this)) {
            if (!this.collisionMultiple(this.champ.getParticules())) {
                if (!this.collisionSimple(this.champ.getParticules())) {
                    prochaineVitesse = vitesseCourante;
                    prochaineDirection = directionCourante;
                }
            }
        }
    }

    public void effectueDeplacement() {
        vitesseCourante = prochaineVitesse;
        directionCourante = prochaineDirection;

        x += (int) (vitesseCourante * Math.cos(directionCourante));
        y += (int) (vitesseCourante * Math.sin(directionCourante));
        x %= champ.getLargeur();
        y %= champ.getHauteur();
        if (x < 0) {
            x += champ.getLargeur();
        }
        if (y < 0) {
            y += champ.getHauteur();
        }
    }

    public boolean estMorte() {
        return etat.estMorte();
    }

    /**
     * @param c : particules presentes dans le champ de particules
     * @return les particules presentes dans le champ d'action de la particule courante.
     */
    public List<Particule> extraireVoisins(List<Particule> c) {
        List<Particule> result = new ArrayList<>();
        for (Particule p : c) {
            if (p != this && util.DistancesEtDirections.distanceDepuisUnPoint(this.getX(), this.getY(), p.getX(), p.getY()) <= Particule.epaisseur) {
                result.add(p);
            }
        }
        return result;
    }

    /**
     * Cette methode retourne l'ensemble des voisins avec lesquels la particule correcte en collision exclusive.
     */
    public List<Particule> collisionSimpleBilateral(List<Particule> voisins) {
        List<Particule> resultat = this.extraireVoisins(voisins);
        List<Particule> aRetirer = new ArrayList<>();

        for (Particule p : resultat) {
            if (p.extraireVoisins(voisins).size() > 1 || Particule.collisionsSimplesTraitees.contains(p)) {
                aRetirer.add(p);
            }
        }
        resultat.removeAll(aRetirer);
        return resultat;
    }

    /**
     * En cas de collision multiple, i.e. plus de deux particules sont au meme endroit a un instant t,
     * toutes les particules impliquees repartent en direction opposee dans l'etat excite (peu importe l'etat dans lequel
     * la particule etait avant la collision)
     *
     * @param c : represente l'ensemble des particules presentes dans le champ de particules
     * @return : retourne vrai si une collision multiple a eu lieu et faux sinon.
     */
    public boolean collisionMultiple(List<Particule> c) {
        setEtat(etat.collisionMultiple(c));
        return enCollision;
    }

    /**
     * Cette methode sera appelee lorsqu'une collision a lieu
     * entre plusieurs particules.
     * La variable champ represente l'ensemble des particules presentes dans le champ de particules.
     * Les nouvelles entitees eventuellement crees devront etre ajoutees dans le champ de particules.
     */
    public boolean collisionSimple(List<Particule> champ){
        setEtat(etat.collisionSimple(champ));
        return enCollision;
    }

    public void meurt() {
        setEtat(etat.meurt());
    }

    public abstract double getVitesseOriginale();

    public void resetVitesse() {
        this.setProchaineVitesse(getVitesseOriginale());
        this.setVitesseCourante(getVitesseOriginale());
    }

    public abstract Color getCouleur();

    public double getVitesseExcite() {
        return getVitesseOriginale() * 1.5;
    }
}
