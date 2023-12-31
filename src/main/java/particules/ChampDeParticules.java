package particules;

import comportement.Comportement;
import controleur.Controleur;
import etats.EtatParticule;
import visualisation.Observer;
import visualisation.Sujet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ChampDeParticules implements Champ, Sujet {

    private final int largeur;
    private final int hauteur;
    private final List<Particule> population;
    private List<Particule> nouvelleGeneration;
    private Controleur controleur;
    private final List<Observer> observers;
    private List<Particule> aEnvoyerObservateur;
    private boolean extermination = false;

    public ChampDeParticules(int largeur, int longeur) {
        this.largeur = largeur;
        this.hauteur = longeur;
        this.population = new ArrayList<>();
        this.nouvelleGeneration = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.aEnvoyerObservateur = new ArrayList<>();
    }

    public ChampDeParticules(int largeur, int longeur, int nb, ParticuleType typeParticule) {
        this.largeur = largeur;
        this.hauteur = longeur;
        this.population = new ArrayList<>();
        this.nouvelleGeneration = this.generationParticule(nb, typeParticule);
        this.observers = new ArrayList<>();
    }

    public void setControleur(Controleur c) {
        this.controleur = c;
    }

    public void ajouterUnePopulation(ParticuleType type, int nb) {
        this.nouvelleGeneration.addAll(this.generationParticule(nb, type));
    }

    @Override
    public void naissance(ParticuleType type, double x, double y) {
        Particule p = FabriqueParticule.creationParticuleType(x, y, type, this);
        for (int i = 0; i < 2; i++) {
            p.effectueDeplacement();
        }
        this.nouvelleGeneration.add(p);
    }

    @Override
    public void ajouterManuellement(Particule p) {
        this.nouvelleGeneration.add(p);
    }

    @Override
    public int getLargeur() {
        return largeur;
    }

    @Override
    public int getHauteur() {
        return hauteur;
    }

    private ArrayList<Particule> generationParticule(int nb, ParticuleType typeParticule) {
        return FabriqueParticule.generationParticule(nb, getLargeur(), getHauteur(), typeParticule, this);
    }

    @Override
    public List<Particule> getParticules() {
        return population;
    }

    @Override
    public void supprimerLesParticulesDecedees() {
        if (extermination) {
            this.population.forEach(Particule::meurt);
            extermination = false;
        }

        HashSet<Particule> particulesMortes = new HashSet<>();
        for (Particule p : this.population) {
            if (p.estMorte()) {
                particulesMortes.add(p);
            }
        }
        if (particulesMortes.isEmpty()) {
            return;
        }

        this.population.removeAll(particulesMortes);
        this.aEnvoyerObservateur.addAll(particulesMortes);
        notifyObserversRemove();
    }

    public void updatePopulation() {
        if (this.nouvelleGeneration.isEmpty()) {
            return;
        }
        this.population.addAll(this.nouvelleGeneration);
        this.aEnvoyerObservateur.addAll(this.nouvelleGeneration);
        notifyObserversAdd();
        this.nouvelleGeneration = new ArrayList<>();
        this.controleur.getApplication().majParticulesADessiner();
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObserversRemove() {
        for (Observer o : this.observers) {
            o.updateRemove(this.aEnvoyerObservateur);
        }
        this.aEnvoyerObservateur = new ArrayList<>();
    }

    @Override
    public void notifyObserversAdd() {
        for (Observer o : this.observers) {
            o.updateAdd(this.aEnvoyerObservateur);
        }
        this.aEnvoyerObservateur = new ArrayList<>();
    }

    @Override
    public void notifyObserversEtat(Particule p, EtatParticule etat) {
        for (Observer o : this.observers) {
            o.updateEtat(p.getEtat(), etat);
        }
    }

    @Override
    public void notifyObserversComportement(Particule p, Comportement comportement) {
        for (Observer o : this.observers) {
            o.updateComportement(p.getComportement(), comportement);
        }
    }

    @Override
    public void exterminer() {
        extermination = true;
    }
}
