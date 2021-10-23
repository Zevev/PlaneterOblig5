package no.hiof.magnufa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class PlanetSystem implements Comparable<PlanetSystem> {
    private String name, pictureUrl;
    private Star centerStar;
    private ArrayList<Planet> planets = new ArrayList<Planet>();

    public PlanetSystem(String name, Star centerStar) {
        this(name, centerStar, "");
    }

    public PlanetSystem(String name, Star centerStar, String pictureUrl) {
        this.name = name;
        this.centerStar = centerStar;
        this.pictureUrl = pictureUrl;
    }

    public PlanetSystem() {
    }

    public void addPlanet(Planet planet) {
        planets.add(planet);
    }

    public ArrayList<Planet> getPlanets() {
        return new ArrayList<Planet>(planets);
    }

    public void addPlanets(ArrayList<Planet> planets) {
        this.planets.addAll(planets);
    }

    public Planet getPlanet(String name) {
        for (Planet planet : planets) {
            if (planet.getName().equals(name))
                return planet;
        }

        return null;
    }

    /*public Planet getPlanetInSystem(String name, List<PlanetSystem> planetSystems) {
        for (PlanetSystem planetSystem1 : planetSystems) {
            for (PlanetSystem planetSystem2 : planetSystems) {
                if (planetSystems.equals(planetSystem2.name)) {

                }
            }
        }
    }*/

    public void removePlanet(Planet planet) {
        planets.remove(planet);
    }
    @JsonIgnore
    public Planet getSmallestPlanet() {
        if (planets.size() == 0)
            return null;

        Planet smallestPlanet = planets.get(0);

        for (Planet currentPlanet : planets) {
            if (currentPlanet.getRadius() < smallestPlanet.getRadius()) {
                smallestPlanet = currentPlanet;
            }
            else if (currentPlanet.getRadius() == smallestPlanet.getRadius()) {
                if (currentPlanet.getMass() < smallestPlanet.getMass())
                    smallestPlanet = currentPlanet;
            }
        }

        return smallestPlanet;
    }
    @JsonIgnore
    public Planet getLargestPlanet() {
        if (planets.size() == 0)
            return null;

        Planet largestPlanet = planets.get(0);

        for (Planet currentPlanet : planets) {
            if (currentPlanet.getRadius() > largestPlanet.getRadius()) {
                largestPlanet = currentPlanet;
            }
            else if (currentPlanet.getRadius() == largestPlanet.getRadius()) {
                if (currentPlanet.getMass() > largestPlanet.getMass())
                    largestPlanet = currentPlanet;
            }
        }

        return largestPlanet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Star getCenterStar() {
        return centerStar;
    }

    public void setCenterStar(Star centerStar) {
        this.centerStar = centerStar;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return String.format("%s has %d planets that revolve around the star %s", name, planets.size(), centerStar.getName());
    }

    @Override
    public int compareTo(PlanetSystem sammenligningsPlanet) {
        return this.name.compareTo(sammenligningsPlanet.getName());
    }
}
