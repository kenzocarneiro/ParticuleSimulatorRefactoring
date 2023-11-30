package particules;

public class FabriqueParticuleB extends FabriqueParticule {
    private static FabriqueParticuleB instance;

    private FabriqueParticuleB() {}

    public static FabriqueParticuleB getInstance() {
        if (instance == null) {
            instance = new FabriqueParticuleB();
        }
        return instance;
    }

    @Override
    public Particule creationParticule(Champ c, double x, double y, double dC) {
        return new ParticuleB(c, x, y, dC);
    }

}
