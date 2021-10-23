package no.hiof.magnufa.repository;

import no.hiof.magnufa.model.Planet;
import no.hiof.magnufa.model.PlanetSystem;
import no.hiof.magnufa.model.Star;

import java.util.ArrayList;
import java.util.List;

public class UniverseRepository implements IUniversityRepository {
    private ArrayList<PlanetSystem> planetSystems = new ArrayList<>();

    public UniverseRepository() {
        generateSolarSystem();
        generateKepler11PlanetSystem();
    }

    private void generateSolarSystem() {
        Star theSun = new Star("Sun", 1.98892E30, 695700, 5777);

        PlanetSystem solarSystem = new PlanetSystem("Solar System", theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/The_Sun_as_seen_in_January_1st_2015.jpg/480px-The_Sun_as_seen_in_January_1st_2015.jpg");

        solarSystem.addPlanet(new Planet("Mercury", 3.283E23,2439.7, 0.387, 0.206, 88, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Transit_Of_Mercury%2C_May_9th%2C_2016.png/480px-Transit_Of_Mercury%2C_May_9th%2C_2016.png"));
        solarSystem.addPlanet(new Planet("Venus",4.867E24,6051.8,0.723, 0.007, 225, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/SDO%27s_Ultra-high_Definition_View_of_2012_Venus_Transit_%28304_Angstrom_Full_Disc_02%29.jpg/480px-SDO%27s_Ultra-high_Definition_View_of_2012_Venus_Transit_%28304_Angstrom_Full_Disc_02%29.jpg"));
        solarSystem.addPlanet(new Planet("Earth",5.972E24,6371,1, 0.017, 365, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/NASA_Earth_America_2002.jpg/480px-NASA_Earth_America_2002.jpg"));
        solarSystem.addPlanet(new Planet("Mars",6.39E23,3389.5, 1.524, 0.093, 687, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Mars_23_aug_2003_hubble.jpg/480px-Mars_23_aug_2003_hubble.jpg"));
        solarSystem.addPlanet(new Planet("Jupiter",1.898E27,69911, 5.20440, 0.049, 4380, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Jupiter_and_its_shrunken_Great_Red_Spot.jpg/480px-Jupiter_and_its_shrunken_Great_Red_Spot.jpg"));
        solarSystem.addPlanet(new Planet("Saturn",5.683E26,58232, 9.5826, 0.057, 10585, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Saturn_from_Cassini_Orbiter_-_Square_%282004-10-06%29.jpg/480px-Saturn_from_Cassini_Orbiter_-_Square_%282004-10-06%29.jpg"));
        solarSystem.addPlanet(new Planet("Uranus",8.681E25,25362, 19.2184, 0.046, 30660, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Uranus2.jpg/480px-Uranus2.jpg"));
        solarSystem.addPlanet(new Planet("Neptune",1.024E26,24622, 30.11, 0.010, 60225, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Neptune_Full_%28cropped%29.jpg/480px-Neptune_Full_%28cropped%29.jpg"));

        planetSystems.add(solarSystem);
    }

    private void generateKepler11PlanetSystem() {
        Star kepler11 = new Star("Kepler-11", 1.889E30, 710310, 5680, "https://upload.wikimedia.org/wikipedia/commons/6/64/Kepler11.png");

        PlanetSystem kepler11System = new PlanetSystem("Kepler-11 System", kepler11, "https://upload.wikimedia.org/wikipedia/commons/6/64/Kepler11.png");

        kepler11System.addPlanet(new Planet("Kepler-11b", 3.283E23,2439.7, 0.091, 0.045, 10.3039, kepler11));
        kepler11System.addPlanet(new Planet("Kepler-11c",4.867E24,6051.8, 0.107, 0.026, 13.0241, kepler11));
        kepler11System.addPlanet(new Planet("Kepler-11d",5.972E24,6371, 0.155, 0.004, 22.6845, kepler11));
        kepler11System.addPlanet(new Planet("Kepler-11e",6.39E23,3389.5, 0.195, 0.012, 31.9996, kepler11));
        kepler11System.addPlanet(new Planet("Kepler-11f",1.898E27,69911, 0.250, 0.013, 46.6888, kepler11));
        kepler11System.addPlanet(new Planet("Kepler-11g",5.683E26,58232, 0.466, 0.015, 118.3807, kepler11));

        planetSystems.add(kepler11System);
    }

    @Override
    public PlanetSystem getPlanetSystem(String planetSystemName) {
        for (PlanetSystem planetSystem : planetSystems) {
            if (planetSystem.getName().equals(planetSystemName)) {
                return planetSystem;
            }
        }

        return null;
    }



    @Override
    public ArrayList<PlanetSystem> getPlanetSystems() {
        return new ArrayList<>(planetSystems);
    }

    @Override
    public Planet getPlanet(String planetSystemName, String planetName) {
        return getPlanetSystem(planetSystemName).getPlanet(planetName);
    }

    @Override
    public ArrayList<Planet> getPlanets(String planetSystemName) {
        return getPlanetSystem(planetSystemName).getPlanets();
    }

    @Override
    public void createPlanet(String systemName, String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl) {

    }

    @Override
    public void updatePlanet(String systemName, String currentName, String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl) {

    }





    @Override
    public void removePlanet(String systemName, String planetName) {

    }
}
