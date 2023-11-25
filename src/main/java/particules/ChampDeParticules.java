package particules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import controleur.Controleur;

public class ChampDeParticules implements Champ {

	
	private int largeur;
	private int hauteur;
	private List<Particule> population ;
	private List<Particule> nouvelleGeneration;
	private Controleur controleur ;


	public ChampDeParticules(int largeur, int longeur) {
		
		this.largeur = largeur;
		this.hauteur = longeur;
		this.population = new ArrayList<Particule>();
		this.nouvelleGeneration = new ArrayList<Particule>();
	}
	
	
	public void setControleur(Controleur c) {
		this.controleur = c;
	}
	
	
	public ChampDeParticules(int largeur, int longeur, int nb, int typeParticule) {
		
		this.largeur = largeur;
		this.hauteur = longeur;
		this.population = new ArrayList<Particule>();
		this.nouvelleGeneration = this.generationParticule(nb,typeParticule);

	}
	
	
	public void ajouterUnePopulation(int type, int nb) {
		this.nouvelleGeneration.addAll(this.generationParticule(nb,type));
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
		double direction =  (generateur.nextFloat() * 2 * Math.PI);
		
		
		Particule result = null;
		
		switch(typeParticule) {
		case 0: {
			result =  new ParticuleA(this,x,y,direction);
			break;
		}
		
		case 1: {
			result =	new ParticuleB(this,x,y,direction);
			break;
		}
		}
		return result;
	}
	
	private ArrayList<Particule> generationParticule(int nb, int typeParticule) {
		ArrayList<Particule> nouvelleGeneration = new ArrayList<Particule>();
		Random generateur = new Random();
		int epaisseur = 0;
		
		switch(typeParticule) {
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
			if (x > largeur - epaisseur)
				x -= epaisseur;
			int y = (int) (generateur.nextFloat() * hauteur);
			if (y > hauteur - epaisseur)
				y -= epaisseur;
			
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
		HashSet<Particule> particulesMortes  = new HashSet<Particule>();
		for(Particule p : this.population) {
			if (p.estMorte()) {
				particulesMortes.add(p);
			}
		}
		
		this.population.removeAll(particulesMortes);

	}
	
	
	public void updatePopulation() {
		this.population.addAll(this.nouvelleGeneration);
		this.nouvelleGeneration = new ArrayList<Particule>();
	}
}

	
	