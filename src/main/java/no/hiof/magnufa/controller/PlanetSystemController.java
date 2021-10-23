package no.hiof.magnufa.controller;

import io.javalin.http.Context;
import no.hiof.magnufa.repository.IUniversityRepository;


public class PlanetSystemController {
    private IUniversityRepository universityRepository;

    public PlanetSystemController(IUniversityRepository universeRepository) {
        this.universityRepository = universeRepository;
    }

    public void getAllPlanetSystems(Context context) {
        context.json(universityRepository.getPlanetSystems());
    }

    public void getPlanetSystem(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        context.json(universityRepository.getPlanetSystem(planetSystemName));
    }

}
