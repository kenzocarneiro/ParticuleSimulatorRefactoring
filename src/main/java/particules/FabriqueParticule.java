package particules;

import java.util.Random;

public abstract class FabriqueParticule {

    protected enum Type {A, B, C}

    public abstract Particule creationParticule(Champ c, double x, double y, double dC);

    public static Particule creationParticuleType(double x, double y, int typeParticule, Champ champ) {
        Random generateur = new Random();
        double direction = (generateur.nextFloat() * 2 * Math.PI);

        Particule result = null;

        switch (typeParticule) {
            case 0: {
                result = FabriqueParticuleA.getInstance().creationParticule(champ, x, y, direction);
                break;
            }

            case 1: {
                result = FabriqueParticuleB.getInstance().creationParticule(champ, x, y, direction);
                break;
            }
        }
        return result;
    }
}

