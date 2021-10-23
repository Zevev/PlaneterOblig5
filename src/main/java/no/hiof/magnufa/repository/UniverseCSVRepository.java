package no.hiof.magnufa.repository;

import no.hiof.magnufa.model.Planet;
import no.hiof.magnufa.model.PlanetSystem;
import no.hiof.magnufa.model.Star;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UniverseCSVRepository extends Thread implements IUniversityRepository {
    private String filnavn;

    public UniverseCSVRepository(String filnavn) {
        this.filnavn = filnavn;
    }

    public HashMap<String, PlanetSystem> lesFraCSV(File filnavn) {
        HashMap<String, Star> starHashMap = new HashMap<String, Star>();
        HashMap<String, PlanetSystem> planetSystemHashMap = new HashMap<String, PlanetSystem>();
        HashMap<String, Planet> planetHashMap = new HashMap<String, Planet>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filnavn))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitter = line.split(",");

                if (!starHashMap.containsKey(splitter[2]))
                    starHashMap.put(splitter[2],new Star(splitter[2],Double.parseDouble(splitter[3]),Double.parseDouble(splitter[4]),Double.parseDouble(splitter[5]),splitter[6]));

                if (!planetSystemHashMap.containsKey(splitter[0]))
                    planetSystemHashMap.put(splitter[0],new PlanetSystem(splitter[0], starHashMap.get(splitter[2]), splitter[1]));

                if (!planetHashMap.containsKey(splitter[7]))
                    planetHashMap.put(splitter[7],new Planet(splitter[7],Double.parseDouble(splitter[8]),Double.parseDouble(splitter[9]),Double.parseDouble(splitter[10]),Double.parseDouble(splitter[11]),Double.parseDouble(splitter[12]),starHashMap.get(splitter[2]),splitter[13]));

                if (planetSystemHashMap.get(splitter[0]).getCenterStar() == planetHashMap.get(splitter[7]).getCentralCelestialBody())
                    planetSystemHashMap.get(splitter[0]).addPlanet(planetHashMap.get(splitter[7]));
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }


        return planetSystemHashMap;
            }

            public static void skrivTilCSV(List<PlanetSystem> planetSystems, File fileWrite) {
                Thread anotherThread = new Thread() {
                    @Override
                    public void run() {
                        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWrite))) {
                            for (PlanetSystem planetSystem : planetSystems) {
                                for (Planet planet : planetSystem.getPlanets()) {
                                    bufferedWriter.write(planetSystem.getName() + "," + planetSystem.getPictureUrl() + "," + planetSystem.getCenterStar() + "," + planetSystem.getCenterStar().getMass() + "," + planetSystem.getCenterStar().getRadius() + "," + planetSystem.getCenterStar().getEffectiveTemperature() + "," + planetSystem.getCenterStar().getPictureUrl() + "," + planet.getName() + "," + planet.getMass() + "," + planet.getRadius() + "," + planet.getSemiMajorAxis() + "," + planet.getEccentricity() + "," + planet.getOrbitalPeriod() + "," + planet.getPictureUrl());
                                    bufferedWriter.newLine();
                                }
                            }
                        } catch (FileNotFoundException fnfe) {
                            System.out.println(fnfe.getMessage());
                        } catch (IOException ioexc) {
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


    public List<PlanetSystem> getPlanetSystems() {
        HashMap<String, PlanetSystem> planetSystemHashMap = lesFraCSV(new File("testcsv.csv"));
        List<PlanetSystem> planetSystems = new ArrayList<>();
        planetSystemHashMap.forEach((s, planetSystem) -> planetSystems.add(planetSystem));
        return planetSystems;
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
    public void createPlanet(String systemName, String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl) {
        List<PlanetSystem> planetSystems = new ArrayList<>(getPlanetSystems());
        for (PlanetSystem planetSystem1 : planetSystems) {
            if (systemName.equals(planetSystem1.getName())) {
                planetSystem1.addPlanet(new Planet(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, pictureUrl));

            }
        }
        skrivTilCSV(planetSystems , new File("testcsv.csv"));
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
        skrivTilCSV(planetSystems,new File("testcsv.csv"));
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

        skrivTilCSV( planetSystems , new File("testplanetcsv.csv"));

    }
}

