package no.hiof.magnufa;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.hiof.magnufa.model.NaturalSatellite;
import no.hiof.magnufa.model.Planet;
import no.hiof.magnufa.model.PlanetSystem;
import no.hiof.magnufa.model.Star;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

	    Star theSun = new Star("Sun", 695700, 1.98892E30, 5777);

	    PlanetSystem solarSystem = new PlanetSystem("Solar System", theSun);

	    solarSystem.addPlanet(new Planet("Mercury", 3.283E23,2439.7, 0.387, 0.206, 88, theSun));
		solarSystem.addPlanet(new Planet("Venus",4.867E24,6051.8, 0.723, 0.007, 225, theSun));
		solarSystem.addPlanet(new Planet("Earth",5.972E24,6371, 1, 0.017, 365, theSun));
		solarSystem.addPlanet(new Planet("Mars",6.39E23,3389.5, 1.524, 0.093, 687, theSun));
		solarSystem.addPlanet(new Planet("Jupiter",1.898E27,69911, 5.20440, 0.049, 4380, theSun));
		solarSystem.addPlanet(new Planet("Saturn",5.683E26,58232, 9.5826, 0.057, 10585, theSun));
		solarSystem.addPlanet(new Planet("Uranus",8.681E25,25362, 19.2184, 0.046, 30660, theSun));
		solarSystem.addPlanet(new Planet("Neptune",1.024E26,24622, 30.11, 0.010, 60225, theSun));

        System.out.println(solarSystem);

        Planet mercury = solarSystem.getPlanet("Mercury");
        Planet venus = solarSystem.getPlanet("Venus");
        Planet earth = solarSystem.getPlanet("Earth");
        Planet mars = solarSystem.getPlanet("Mars");
        Planet jupiter = solarSystem.getPlanet("jupiter");
		Planet saturn = solarSystem.getPlanet("Saturn");
		Planet uranus = solarSystem.getPlanet("Uranus");
		Planet neptune = solarSystem.getPlanet("Neptune");

        System.out.println("First planet: " + mercury);
        System.out.println("Third planet: " + earth);

		System.out.println(String.format("%s has a Jupiter Mass of %f and a Jupiter Radius of %f", saturn.getName(), saturn.getMassInMjup(), saturn.getRadiusInRjup()));

		System.out.println(String.format("%s has a Solar Mass of %f and a Solar Radius of %f ", theSun.getName(), theSun.getMassInMsun(), theSun.getReadiusInRsun()));

		System.out.println(String.format("%s has a Earth Mass of %f and a Earth Radius of %f ", mars.getName(), mars.getMassInMearth(), mars.getRadiusInRearth()));

		System.out.println("The surface gravity of " + earth.getName() + " is " + earth.getSurfaceGravity());
		System.out.println("The surface gravity of " + neptune.getName() + " is " + neptune.getSurfaceGravity());

		Planet smallestPlanet = solarSystem.getSmallestPlanet();
		Planet largestPlanet = solarSystem.getLargestPlanet();

		System.out.println(smallestPlanet.getName() + " is the smallest planet in the " + solarSystem.getName());
		System.out.println(largestPlanet.getName() + " is the largest planet in the " + solarSystem.getName());


		// Oppgave 2.5
		System.out.println("\n************Orbital Distance************");
		printDistanceBetweenNaturalSatteliteAndCentralBody(earth);


		// Oppgave 2.6
		System.out.println("\n************Orbital Velocity************");
		printOrbitalVelocityForNaturalSattelite(earth);


		ArrayList<Planet> planets = solarSystem.getPlanets();

		System.out.println("\n************Unsorted************");
		for (Planet planet : planets) {
			System.out.println(planet);
		}

		Collections.sort(planets);

		System.out.println("\n************Sorted************");
		for (Planet planet : planets) {
			System.out.println(planet);
		}

		List<PlanetSystem> nyListe = lesFraJSON(new File("planets_100.json"));
		System.out.println(nyListe);

    }

	private static void printDistanceBetweenNaturalSatteliteAndCentralBody(NaturalSatellite aSattelite) {
    	for (int i = 0; i <= 360; i += 90) {
    		double distance = aSattelite.distanceToCentralBody(i);
			System.out.println(String.format("%s has a distance of %.0fkm to the %s at %d degrees", aSattelite.getName(), distance, aSattelite.getCentralCelestialBody().getName(), i));
		}
	}

	private static void printOrbitalVelocityForNaturalSattelite(Planet aSattelite) {
    	for (int i = 0; i <= 180; i += 45) {
    		double distance = aSattelite.distanceToCentralBody(i);
    		double velocity = aSattelite.orbitingVelocity(distance);
			System.out.println(String.format("At a distance of %.0fkm, %s has a velocity of %.2fkm/s", distance, aSattelite.getName(), velocity));

		}
	}
	public static List<PlanetSystem> lesFraJSON(File filnavn) {
		List<PlanetSystem> planetSystems = new ArrayList<>();

		try {
			ObjectMapper objectMapper = new ObjectMapper();

			PlanetSystem[] planetSystemArray = objectMapper.readValue(filnavn, PlanetSystem[].class);

			planetSystems = Arrays.asList(planetSystemArray);
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}

		return planetSystems;
	}

}
