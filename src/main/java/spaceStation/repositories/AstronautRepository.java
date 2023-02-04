package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AstronautRepository implements Repository<Astronaut> {

    private Collection<Astronaut> astronauts;

    public AstronautRepository() {
        this.astronauts = new ArrayList<Astronaut>();
    }

    @Override
    public Collection<Astronaut> getModels() {
        return Collections.unmodifiableCollection(astronauts);
    }

    @Override
    public void add(Astronaut astronaut) {
        boolean isExist = false;
        for (Astronaut astronaut1 : astronauts) {
            if (astronaut1.getName().equals(astronaut.getName())) {
                isExist = true;
            }
        }
        if (!isExist) {
            astronauts.add(astronaut);
        }
    }

    @Override
    public boolean remove(Astronaut astronaut) {
        for (Astronaut astronaut1 : astronauts) {
            if (astronaut1.getName().equals(astronaut.getName())) {
                astronauts.remove(astronaut);
                return true;
            }
        }
        return false;
    }

    @Override
    public Astronaut findByName(String name) {

        return astronauts.stream().filter(astronaut -> astronaut.getName().equals(name))
                .findFirst().orElse(null);
    }
}
