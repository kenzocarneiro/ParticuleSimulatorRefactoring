package particules;

public class FabriqueParticuleA extends FabriqueParticule {

    public static Particule creationParticule(Champ c, double x, double y, double dC) {
        return new ParticuleA(c, x, y, dC);
    }
}
