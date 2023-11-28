package particules;

public class FabriqueParticuleB extends FabriqueParticule {

    public static Particule creationParticule(Champ c, double x, double y, double dC) {
        return new ParticuleB(c, x, y, dC);
    }
}
