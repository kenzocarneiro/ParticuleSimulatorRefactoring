package particules;

import java.util.List;

import particules.Particule.Phase;


public class ParticuleB extends Particule {

	
	
	
	public ParticuleB (Champ c, double x, double y,
			double dC) {
		champ = c;
		this.x = x;
		this.y = y;
		directionCourante = dC;
		vitesseCourante = 30f;
		this.prochaineDirection = dC;
		this.prochaineVitesse = 30f;
		this.phaseDeLaParticule = Phase.JEUNE;
		this.etatDeLaParticule = Etat.NORMAL;
		this.passageACTIVE = 100;
		this.passageFINDEVIE = 300;
		this.passageMORT = 700;
		
	}
	

	public void resetVitesse() {
		this.prochaineVitesse = 30f;
	}
	

	@Override
	public boolean collisionSimple(List<Particule> c) {
		List<Particule> enCollisionFrontale = this.collisionSimpleBilateral(this.champ.getParticules());
		
		if (enCollisionFrontale.size() != 1) 
			return false;
		else {
			Particule.collisionsSimplesTraitees.add(this);
			if (this.directionCourante > Math.PI) {
				this.prochaineDirection = this.directionCourante - Math.PI;
			}
			else { 
				this.prochaineDirection = this.directionCourante + Math.PI;
			}
			
			
			for (Particule p : enCollisionFrontale) {
				if (p.directionCourante > Math.PI)
					p.prochaineDirection = p.directionCourante - Math.PI;
				else 
					p.prochaineDirection = p.directionCourante + Math.PI;
				Particule.collisionsSimplesTraitees.add(p);
				if (p.etatDeLaParticule == Etat.EXCITE && this.etatDeLaParticule == Etat.EXCITE && p.phaseDeLaParticule == Phase.ACTIVE && this.phaseDeLaParticule == Phase.ACTIVE) {
					if (p.getClass() == ParticuleB.class) {
						this.champ.naissance(1, this.x, this.y);
						p.phaseDeLaParticule = Phase.MORTE;
						this.phaseDeLaParticule = Phase.MORTE;
						this.champ.getParticules().remove(this);
						this.champ.getParticules().remove(p);
						
					}
				
					if (p.getClass() == ParticuleA.class) {
						this.resetVitesse();
						p.resetVitesse();
						this.champ.naissance(0,this.x, this.y);
							
					}
				}
				else  {
					if (this.etatDeLaParticule == Etat.NORMAL) {
						this.etatDeLaParticule = Etat.EXCITE;
						this.augmentationVitesse() ;
						
					}
					else {
						this.etatDeLaParticule = Etat.NORMAL;
						this.resetVitesse();
					}
					
					if (p.etatDeLaParticule == Etat.NORMAL) {
						p.etatDeLaParticule = Etat.EXCITE;
						p.augmentationVitesse() ;
					} 
					else {
						p.etatDeLaParticule = Etat.NORMAL;
						p.resetVitesse();
					}
					
					
					
				}
				
			}
		}
		
		
		
		return true;

	}


}
