package controleur;

import particules.Champ;
import particules.FabriqueChampDeParticules;
import particules.Particule;
import particules.ParticuleType;
import simulation.FabriqueSimulateur;
import simulation.Simulateur;
import visualisation.FabriqueVueApplication;
import visualisation.VueApplication;

import java.util.List;

public class Controleur {

    /**
     * champParticules est le modèle de notre application. Dans cet objet nous
     * retrouvons entre autre toutes les particules.
     */
    private final Champ champParticules;
    /**
     * Application est la vue de notre application.
     */
    private final VueApplication application;
    /**
     * sim est l'objet permettant d'animer le modèle.
     * C'est à dire qu'il gère le calcul du prochain état de chaque particule ainsi que le déplacement
     * à l'interieur d'un thread.
     */
    private Simulateur sim = null;

    /**
     * Création d'un champ contenant une population de particules d'une famille
     *
     * @param lib     : libelle de la fenetre representant le champ de particule
     * @param largeur : largeur du champ de particule
     * @param hauteur : hauteur du champ de particule
     * @param nb      : nombre de particule a creer initialement
     * @param type    : type des particules a creer initialement
     */
    public Controleur(String lib, int largeur, int hauteur, int nb, ParticuleType type) {
        champParticules = FabriqueChampDeParticules.getInstance().creationChampDeParticules(largeur, hauteur, nb, type);
        this.sim = FabriqueSimulateur.getInstance().creationSimulateur(30, this);
        this.application = FabriqueVueApplication.getInstance().creationVueApplication(lib, this);
    }

    /**
     * Création d'un champ vide
     *
     * @param lib     : libelle de la fenetre representant le champ de particule
     * @param largeur : largeur du champ de particules
     * @param hauteur : hauteur du champ de particules
     */
    public Controleur(String lib, int largeur, int hauteur) {
        champParticules = FabriqueChampDeParticules.getInstance().creationChampDeParticules(largeur, hauteur);
        this.sim = FabriqueSimulateur.getInstance().creationSimulateur(30, this);
        this.application = FabriqueVueApplication.getInstance().creationVueApplication(lib, this);
    }

    public VueApplication getApplication() {
        return application;
    }

    /**
     * Permet de lancer la simulation
     */
    public void lancerSimulation() {
        this.champParticules.setControleur(this);
        this.champParticules.addObserver(this.application);
        this.sim.demarre();
    }

    /**
     * Permet d'ajouter une population de particules. Mise à jour de la vue dans la foulée.
     *
     * @param nb   : nombre de particules a creer
     * @param type : type de particules a creer
     */
    public void ajouterPopulation(int nb, ParticuleType type) {
        this.champParticules.ajouterUnePopulation(type, nb);
        this.champParticules.updatePopulation();
        this.application.majParticulesADessiner();
    }

    /**
     * Permet de signaler à la vue qu'il faut se redessiner étant donné qu'il
     * y a eu des naissances de particules.
     */
    public void populationEtendueInVivo() {
        this.application.majParticulesADessiner();
    }

    public Champ getchampParticules() {
        return this.champParticules;
    }

    public List<Particule> getPopulationModele() {
        return champParticules.getParticules();
    }

    /**
     * Permet de lancer une maj au niveau du modèle et de supprimer toutes les particules
     * décédées.
     */
    public void majModele() {
        this.champParticules.supprimerLesParticulesDecedees();
    }

    public void majVue() {
        this.application.repaint();
    }

    /**
     * On demande au modèle d'ajouter les nouvelles particules créées au champ de
     * particules. Puis on redessine le champ.
     */
    public void integrationNouvelleGeneration() {
        this.champParticules.updatePopulation();
        this.application.repaint();
    }
}
