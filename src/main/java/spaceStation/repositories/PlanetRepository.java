package spaceStation.repositories;

import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PlanetRepository implements Repository<Planet>{

    private Collection<Planet> planets;

    public PlanetRepository() {
        this.planets = new ArrayList<>();
    }

    @Override
    public Collection<Planet> getModels() {
        return Collections.unmodifiableCollection(planets);
    }

    @Override
    public void add(Planet planet) {
        boolean planetExist = false;
        for (Planet planet1 : planets) {
            if (planet1.getName().equals(planet.getName())) {
                planetExist = true;
            }
        }
        if (!planetExist) {
            planets.add(planet);
        }
    }

    @Override
    public boolean remove(Planet planet) {
        for (Planet planet1 : planets) {
            if (planet1.equals(planet)) {
                planets.remove(planet);
                return true;
            }
        }
        return false;
    }

    @Override
    public Planet findByName(String name) {
        return planets.stream().filter(planet -> planet.getName().equals(name))
                .findFirst().orElse(null);
    }
}
