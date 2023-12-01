package particules;

public class FabriqueParticuleC extends FabriqueParticule {
    private static FabriqueParticuleC instance;

    private FabriqueParticuleC() {}

    public static FabriqueParticuleC getInstance() {
        if (instance == null) {
            instance = new FabriqueParticuleC();
        }
        return instance;
    }

    @Override
    public Particule creationParticule(Champ c, double x, double y, double dC, boolean epileptique) {
        return new ParticuleC(c, x, y, dC, false);
    }

}
