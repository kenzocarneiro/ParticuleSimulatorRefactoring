package simulation;

import java.util.ArrayList;
import java.util.Collection;
import controleur.Controleur;

import particules.Particule;


public class Simulateur implements Runnable {

	protected Controleur c;

	protected int delaiSimulation; // en ms

	protected Thread threadSimulation = null;



	public int getDelaiSimulation() {
		return delaiSimulation;
	}

	public void setDelaiSimulation(int delaiSimu) {
		this.delaiSimulation = delaiSimu;
	}

	public void demarre() {
		threadSimulation = new Thread(this);
		threadSimulation.start();
	}

	public void arrete() {
		threadSimulation = null; // ceci sera teste dans le run()
	}

	public void run() {
		// Code du thread pour arret propre (cf. sun technical tips)
		Thread currentThread = Thread.currentThread();
		while (threadSimulation == currentThread) {
			synchronized(this.c.getPopulationModele()) {
				iteration();
				this.c.majVue();
			}
			try {
				Thread.sleep(delaiSimulation);
			} catch (InterruptedException e) {
			}
		}
	}

	public void iteration() {
		synchronized(this.c.getPopulationModele()) {
			Particule.collisionsSimplesTraitees = new ArrayList<Particule>();
			this.c.majModele();
			Collection<Particule> copieLocale = new ArrayList<Particule>();
			copieLocale.addAll(this.c.getPopulationModele());
			for (Particule a : copieLocale) {
				a.calculeDeplacementAFaire();	    		
			}

			this.c.integrationNouvelleGeneration();
			
			for (Particule a : copieLocale) {
				a.effectueDeplacement();	  
			}
			
			
			
		}
	}

	
	
	public Simulateur(int delaiSimulation, Controleur c) {
		this.delaiSimulation = delaiSimulation;
		this.c = c;
	}
	
	
}
