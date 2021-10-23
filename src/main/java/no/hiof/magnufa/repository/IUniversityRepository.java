package no.hiof.magnufa.repository;

import no.hiof.magnufa.model.Planet;
import no.hiof.magnufa.model.PlanetSystem;

import java.util.ArrayList;
import java.util.List;

public interface IUniversityRepository {
    PlanetSystem getPlanetSystem(String planetSystemName);
    List<PlanetSystem> getPlanetSystems();
    Planet getPlanet(String planetSystemName, String planet);
    ArrayList<Planet> getPlanets(String planetSystemName);
    void createPlanet(String systemName, String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl);
    void updatePlanet(String systemName,String currentName, String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl);
    void removePlanet(String systemName, String planetName);
}
