package no.hiof.magnufa;

import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.VueComponent;
import no.hiof.magnufa.controller.PlanetController;
import no.hiof.magnufa.controller.PlanetSystemController;
import no.hiof.magnufa.repository.UniverseCSVRepository;
import no.hiof.magnufa.repository.UniverseJSONRepository;
import no.hiof.magnufa.repository.UniverseRepository;

public class Application {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7001);

        app.config.enableWebjars();

        app.before("/", ctx -> ctx.redirect("/planet-systems"));

        UniverseJSONRepository universeJSONRepository = new UniverseJSONRepository("planets_100.json");
        //UniverseCSVRepository universeCSVRepository = new UniverseCSVRepository("planets_100.csv");
        //UniverseRepository universeRepository = new UniverseRepository();
        PlanetSystemController planetSystemController = new PlanetSystemController(universeJSONRepository);
        PlanetController planetController = new PlanetController(universeJSONRepository);

        app.get("/planet-systems", new VueComponent("planet-system-overview"));
        app.get("/planet-systems/:planet-system-id", new VueComponent("planet-system-detail"));
        app.get("/planet-systems/:planet-system-id/planets/:planet-id", new VueComponent("planet-detail"));
        app.get("/planet-systems/:planet-system-id/createplanet", new VueComponent("planet-create"));
        app.get("/planet-systems/:planet-system-id/planets/:planet-id/update", new VueComponent("planet-update"));


        app.get("api/planet-systems", planetSystemController::getAllPlanetSystems);
        app.get("api/planet-systems/:planet-system-id", planetSystemController::getPlanetSystem);

        app.get("/api/planet-systems/:planet-system-id/planets", planetController::getPlanets);
        app.get("/api/planet-systems/:planet-system-id/planets/:planet-id", planetController::getPlanet);
        app.get("/api/planet-systems/:planet-system-id/planets/:planet-id/delete", planetController::removePlanet);
        app.post("/api/planet-systems/:planet-system-id/createplanet", planetController::createPlanet);
        app.post("/api/planet-systems/:planet-system-id/planets/:planet-id/update",planetController::updatePlanet);



    }
}
