package particules;

import java.util.ArrayList;
import java.util.Random;

import comportement.ComportementEpileptique;
import comportement.ComportementNormal;

public abstract class FabriqueParticule {

    public abstract Particule creationParticule(Champ c, double x, double y, double dC);

    public static Particule creationParticuleType(double x, double y, ParticuleType typeParticule, Champ champ) {
        Random generateur = new Random();
        double direction = (generateur.nextFloat() * 2 * Math.PI);

        Particule result = null;

        switch (typeParticule) {
            case A: {
                result = FabriqueParticuleA.getInstance().creationParticule(champ, x, y, direction);
                break;
            }

            case B: {
                result = FabriqueParticuleB.getInstance().creationParticule(champ, x, y, direction);
                break;
            }
        }

        if(generateur.nextFloat() < 0.18)
            result.setComportement(new ComportementEpileptique(result));
        else
            result.setComportement(new ComportementNormal(result));

        return result;
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

