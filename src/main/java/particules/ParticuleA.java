package particules;

import java.util.List;


public class ParticuleA extends Particule {

	
	
	
	public ParticuleA (Champ c, double x, double y,
			double dC) {
		this.champ = c;
		this.x = x;
		this.y = y;
		directionCourante = dC;
		vitesseCourante = 10f;
		prochaineDirection = dC;
		prochaineVitesse = 10f;

		this.phaseDeLaParticule = Phase.JEUNE;
		this.etatDeLaParticule = Etat.NORMAL;
		this.passageACTIVE = 500;
		this.passageFINDEVIE = 1500;
		this.passageMORT = 2000;
		
	}
	
	
	
	

	@Override
	public boolean collisionSimple(List<Particule> c)  {
		
		List<Particule> enCollisionFrontale = this.collisionSimpleBilateral(this.champ.getParticules());
		
		if (enCollisionFrontale.size() != 1) 
			return false;
		else {
			Particule.collisionsSimplesTraitees.add(this);
			if (this.directionCourante > Math.PI)
				this.prochaineDirection = this.directionCourante - Math.PI;
			else 
				this.prochaineDirection = this.directionCourante + Math.PI;

			for (Particule p : enCollisionFrontale) {
				if (p.directionCourante > Math.PI)
					p.prochaineDirection = p.directionCourante - Math.PI;
				else 
					p.prochaineDirection = p.directionCourante + Math.PI;
				Particule.collisionsSimplesTraitees.add(p);
				if (p.etatDeLaParticule == Etat.EXCITE && this.etatDeLaParticule == Etat.EXCITE && p.phaseDeLaParticule == Phase.ACTIVE && this.phaseDeLaParticule == Phase.ACTIVE ) {
					if (p.getClass() == ParticuleA.class) {
						this.champ.naissance(0, this.x, this.y);
						p.phaseDeLaParticule = Phase.MORTE;
						this.phaseDeLaParticule = Phase.MORTE;
						this.champ.getParticules().remove(this);
						this.champ.getParticules().remove(p);
						
					}
				
					if (p.getClass() == ParticuleB.class) {
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





	@Override
	public void resetVitesse() {
		this.prochaineVitesse = 10f;
		
	}


}
