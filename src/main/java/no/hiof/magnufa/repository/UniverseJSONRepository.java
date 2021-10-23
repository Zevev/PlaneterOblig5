package no.hiof.magnufa.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.hiof.magnufa.model.Planet;
import no.hiof.magnufa.model.PlanetSystem;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniverseJSONRepository extends Thread implements IUniversityRepository {
    private String filnavn;


    public UniverseJSONRepository(String filnavn) {
        this.filnavn = filnavn;
    }


    public List<PlanetSystem> lesFraJSON(String filnavn) {
        List<PlanetSystem> planetSystems = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            PlanetSystem[] planetSystemArray = objectMapper.readValue(new File(filnavn), PlanetSystem[].class);

            planetSystems = Arrays.asList(planetSystemArray);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        return planetSystems;
    }

    public static void skrivTilJSON(String filnavn, List<PlanetSystem> planetSystems) {

        Thread anotherThread = new Thread() {
            @Override
            public void run() {
                try{
                    File file = new File(filnavn);
                    ObjectMapper objectMapper = new ObjectMapper();

                    objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,planetSystems);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        anotherThread.start();
    }



    @Override
    public PlanetSystem getPlanetSystem(String planetSystemName) {
        for(PlanetSystem planetSystem : getPlanetSystems()) {
            if(planetSystem.getName().equals(planetSystemName)) {
                return planetSystem;
            }
        }
        return null;
    }

    @Override
    public List<PlanetSystem> getPlanetSystems() {
        return lesFraJSON("testjson.json");
        //return new ArrayList<>(lesFraJSON("no/hiof/magnufa/planets_100.json"));
        //return lesFraJSON();
    }

    @Override
    public Planet getPlanet(String planetSystemName, String planet) {
        return getPlanetSystem(planetSystemName).getPlanet(planet);
    }

    @Override
    public ArrayList<Planet> getPlanets(String planetSystemName) {
        return getPlanetSystem(planetSystemName).getPlanets();
    }



    @Override
    public void createPlanet(String systemName,String planetName, double mass, double radius, double semiMajorAxis, double eccentricity,                             double orbitalPeriod, String pictureUrl) {
        List<PlanetSystem> planetSystems = new ArrayList<>(getPlanetSystems());
        for (PlanetSystem planetSystem1 : planetSystems) {
                if (systemName.equals(planetSystem1.getName())) {
                    planetSystem1.addPlanet(new Planet(planetName, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, pictureUrl));

                }
        }
        skrivTilJSON("testjson.json", planetSystems);
    }

    @Override
    public void updatePlanet(String systemName, String currentName, String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl) {
        List<PlanetSystem> planetSystems = new ArrayList<>(getPlanetSystems());
        for (PlanetSystem planetSystem1 : planetSystems) {
            if (systemName.equals(planetSystem1.getName())) {
                planetSystem1.getPlanet(currentName).setName(name);
                planetSystem1.getPlanet(name).setMass(mass);
                planetSystem1.getPlanet(name).setRadius(radius);
                planetSystem1.getPlanet(name).setSemiMajorAxis(semiMajorAxis);
                planetSystem1.getPlanet(name).setEccentricity(eccentricity);
                planetSystem1.getPlanet(name).setOrbitalPeriod(orbitalPeriod);
                planetSystem1.getPlanet(name).setPictureUrl(pictureUrl);

            }
        }
        skrivTilJSON("testjson.json", planetSystems);
    }




    @Override
    public void removePlanet(String systemName, String planetName) {
        List<PlanetSystem> planetSystems = new ArrayList<>(getPlanetSystems());
        for (PlanetSystem planetSystem1 : planetSystems) {
            for (PlanetSystem planetSystem2 : planetSystems) {
                if (systemName.equals(planetSystem2.getName())) {
                    planetSystem2.removePlanet(planetSystem2.getPlanet(planetName));

                }
            }
        }
         skrivTilJSON("testjson.json", planetSystems);

    }


}
