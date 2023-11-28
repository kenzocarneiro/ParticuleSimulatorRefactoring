package particules;

import controleur.Controleur;
import visualisation.Observer;
import visualisation.Sujet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class ChampDeParticules implements Champ, Sujet {

    private final int largeur;
    private final int hauteur;
    private final List<Particule> population;
    private List<Particule> nouvelleGeneration;
    private Controleur controleur;
    private List<Observer> observers;

    public ChampDeParticules(int largeur, int longeur) {
        this.largeur = largeur;
        this.hauteur = longeur;
        this.population = new ArrayList<>();
        this.nouvelleGeneration = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public ChampDeParticules(int largeur, int longeur, int nb, int typeParticule) {
        this.largeur = largeur;
        this.hauteur = longeur;
        this.population = new ArrayList<>();
        this.nouvelleGeneration = this.generationParticule(nb, typeParticule);
        this.observers = new ArrayList<>();
    }

    public void setControleur(Controleur c) {
        this.controleur = c;
    }

    public void ajouterUnePopulation(int type, int nb) {
        this.nouvelleGeneration.addAll(this.generationParticule(nb, type));
    }

    @Override
    public void naissance(int type, double x, double y) {
        this.nouvelleGeneration.add(this.creationParticule(type, x, y));
        this.controleur.populationEtendueInVivo();
    }

    @Override
    public int getLargeur() {
        return largeur;
    }

    @Override
    public int getHauteur() {
        return hauteur;
    }

    private Particule creationParticule(int typeParticule, double x, double y) {
        Random generateur = new Random();
        double direction = (generateur.nextFloat() * 2 * Math.PI);

        Particule result = null;

        switch (typeParticule) {
            case 0: {
                result = new ParticuleA(this, x, y, direction);
                break;
            }

            case 1: {
                result = new ParticuleB(this, x, y, direction);
                break;
            }
        }
        return result;
    }

    private ArrayList<Particule> generationParticule(int nb, int typeParticule) {
        ArrayList<Particule> nouvelleGeneration = new ArrayList<>();
        Random generateur = new Random();
        int epaisseur = 0;

        switch (typeParticule) {
            case 0: {
                epaisseur = ParticuleA.epaisseur;
                break;
            }

            case 1: {
                epaisseur = ParticuleB.epaisseur;
                break;
            }
        }

        for (int i = 0; i < nb; i++) {
            int x = (int) (generateur.nextFloat() * largeur);
            if (x > largeur - epaisseur) x -= epaisseur;
            int y = (int) (generateur.nextFloat() * hauteur);
            if (y > hauteur - epaisseur) y -= epaisseur;

            nouvelleGeneration.add(this.creationParticule(typeParticule, x, y));
        }
        return nouvelleGeneration;
    }

    @Override
    public List<Particule> getParticules() {
        return population;
    }

    @Override
    public void supprimerLesParticulesDecedees() {
        HashSet<Particule> particulesMortes = new HashSet<>();
        for (Particule p : this.population) {
            if (p.estMorte()) {
                particulesMortes.add(p);
            }
        }

        this.population.removeAll(particulesMortes);
        notifyObservers();
    }

    public void updatePopulation() {
        this.population.addAll(this.nouvelleGeneration);
        this.nouvelleGeneration = new ArrayList<>();
        notifyObservers();
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
    public void notifyObservers() {
        for (Observer o : this.observers) {
            o.update(this.population);
        }
    }
}

	
	