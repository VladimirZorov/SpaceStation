package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.bags.Backpack;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private Collection<String> items;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.items = new ArrayList<>();
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
        for (int i = 0; i < items.length; i++) {
            String item = items[i];
            this.items.add(items[i]);
        }

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
        return planetName;
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(REPORT_ASTRONAUT_INFO).append(System.lineSeparator());
        List<Astronaut> astronauts = astronautRepository.getModels().stream().collect(Collectors.toList());
        for (Astronaut astronaut : astronauts) {
            sb.append(String.format(REPORT_ASTRONAUT_NAME, astronaut.getName())).append(System.lineSeparator());
            sb.append(String.format(REPORT_ASTRONAUT_OXYGEN, astronaut.getOxygen())).append(System.lineSeparator());
            sb.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS,
                    String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, astronaut.getBag().getItems()))).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
