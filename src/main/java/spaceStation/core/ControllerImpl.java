package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.bags.Backpack;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private Backpack backpack;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.backpack= new Backpack();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        if (type.equals("Biologist")) {
            astronaut = new Biologist(astronautName);
        } else if (type.equals("Geodesist")) {
            astronaut = new Geodesist(astronautName);
        } else if (type.equals("Meteorologist")) {
            astronaut = new Meteorologist(astronautName);
        } else {
            throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }

        astronautRepository.add(astronaut);
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        PlanetImpl planet = new PlanetImpl(planetName);
        planetRepository.add(planet);
        backpack.getItems().add(String.valueOf(items));
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        boolean astronautExist = false;
        for (Astronaut astronaut : astronautRepository.getModels()) {
            if (astronaut.getName().equals(astronautName)) {
                astronautExist = true;
            }
            if (astronautExist) {
                astronautRepository.remove(astronaut);
                return String.format(ASTRONAUT_RETIRED, astronautName);
            }
        }
        return String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        return null;
    }

    @Override
    public String report() {
        return null;
    }
}
