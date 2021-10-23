package no.hiof.magnufa.controller;

import io.javalin.http.Context;
import no.hiof.magnufa.model.Planet;
import no.hiof.magnufa.repository.IUniversityRepository;
import no.hiof.magnufa.repository.UniverseRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanetController {
    private IUniversityRepository universityRepository;

    public PlanetController(IUniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public void getPlanets(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        String sortBy = context.queryParam("sort_by");

        ArrayList<Planet> planets = universityRepository.getPlanets(planetSystemName);

        if (sortBy != null) {
            switch (sortBy) {
                case "name":
                    Collections.sort(planets);
                    break;
                case "mass":
                    planets.sort((o1, o2) -> (int) (o1.getMass() - o2.getMass()));
                    break;
                case "radius":
                    planets.sort((o1, o2) -> {
                        if (o1.getRadius() < o2.getRadius())
                            return -1;
                        else if(o1.getRadius() > o2.getRadius())
                            return 1;
                        return 0;
                    });
                    break;
            }
        }


        context.json(planets);
    }

    public void getPlanet(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planet-id");

        context.json(universityRepository.getPlanet(planetSystemName, planetName));
    }

    public void removePlanet(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planet-id");


        universityRepository.removePlanet(planetSystemName, planetName);



    }

    public void createPlanet(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        String planetName = context.formParam("name");
        String planetMass = context.formParam("mass");
        String planetRadius = context.formParam("radius");
        String planetSemi = context.formParam("semiMajorAxis");
        String planetEcc = context.formParam("eccentricity");
        String planetOrbital = context.formParam("orbitalPeriod");
        String planetPicture = context.formParam("pictureUrl");
        universityRepository.createPlanet(planetSystemName, planetName, Double.parseDouble(planetMass),Double.parseDouble(planetRadius),            Double.parseDouble(planetSemi), Double.parseDouble(planetEcc), Double.parseDouble(planetOrbital), planetPicture);

        context.redirect("/planet-systems/" + planetSystemName);

    }

    public void updatePlanet(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        String planetCurrentName = context.pathParam("planet-id");
        String planetName = context.formParam("name");
        String planetMass = context.formParam("mass");
        String planetRadius = context.formParam("radius");
        String planetSemi = context.formParam("semiMajorAxis");
        String planetEcc = context.formParam("eccentricity");
        String planetOrbital = context.formParam("orbitalPeriod");
        String planetPicture = context.formParam("pictureUrl");


        universityRepository.updatePlanet(planetSystemName, planetCurrentName,planetName,Double.parseDouble(planetMass),Double.parseDouble(planetRadius),            Double.parseDouble(planetSemi), Double.parseDouble(planetEcc), Double.parseDouble(planetOrbital), planetPicture);

        context.redirect("/planet-systems/" + planetSystemName);
    }
}
