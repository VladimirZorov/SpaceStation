package spaceStation.models.astronauts;

public class Meteorologist extends BaseAstronaut{

    private static final double OXYGEN = 90;

    public Meteorologist(String name) {
        super(name, OXYGEN);
    }

    @Override
    public void breath() {
        if (getOxygen() -10  < 0) {
            setOxygen(0);
        } else {
            setOxygen(getOxygen() - 10);
        }
    }
}
