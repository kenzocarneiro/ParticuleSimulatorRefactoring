package particules;

import java.util.ArrayList;
import java.util.Random;

public abstract class FabriqueParticule {

    public static Particule creationParticuleManuelle(Champ c, double x, double y, double dC, boolean epileptique, ParticuleType typeParticule) {
        switch (typeParticule) {
            case A: {
                return FabriqueParticuleA.getInstance().creationParticule(c, x, y, dC, epileptique);
            }
            case B: {
                return FabriqueParticuleB.getInstance().creationParticule(c, x, y, dC, epileptique);
            }
            case C: {
                return FabriqueParticuleC.getInstance().creationParticule(c, x, y, dC, epileptique);
            }
        }
        throw new IllegalArgumentException("Type de particule inconnu");
    }

    public static Particule creationParticuleType(double x, double y, ParticuleType typeParticule, Champ champ) {
        Random generateur = new Random();
        double direction = (generateur.nextFloat() * 2 * Math.PI);

        boolean epileptique = generateur.nextFloat() < 0.18;

        return creationParticuleManuelle(champ, x, y, direction, epileptique, typeParticule);
    }

    public static ArrayList<Particule> generationParticule(int nb, int largeur, int hauteur, ParticuleType typeParticule, Champ champ) {
        ArrayList<Particule> nouvelleGeneration = new ArrayList<>();
        Random generateur = new Random();
        int epaisseur = Particule.getEpaisseur();

        for (int i = 0; i < nb; i++) {
            int x = (int) (generateur.nextFloat() * largeur);
            if (x > largeur - epaisseur) x -= epaisseur;
            int y = (int) (generateur.nextFloat() * hauteur);
            if (y > hauteur - epaisseur) y -= epaisseur;

            nouvelleGeneration.add(FabriqueParticule.creationParticuleType(x, y, typeParticule, champ));
        }
        return nouvelleGeneration;
    }
}

