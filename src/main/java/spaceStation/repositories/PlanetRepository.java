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

    }

    @Override
    public boolean remove(Planet planet) {
        return false;
    }

    @Override
    public Planet findByName(String name) {
        return null;
    }
}
