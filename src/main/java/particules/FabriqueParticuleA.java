package particules;

public class FabriqueParticuleA extends FabriqueParticule {
    private static FabriqueParticuleA instance;

    private FabriqueParticuleA() {}

    public static FabriqueParticuleA getInstance() {
        if (instance == null) {
            instance = new FabriqueParticuleA();
        }
        return instance;
    }

    public Particule creationParticule(Champ c, double x, double y, double dC) {
        return new ParticuleA(c, x, y, dC);
    }
}
