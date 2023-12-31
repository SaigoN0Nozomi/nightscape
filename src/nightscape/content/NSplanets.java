package nightscape.content;

import arc.graphics.*;
import mindustry.content.Planets;
import mindustry.game.*;
import mindustry.graphics.g3d.*;
import mindustry.maps.planet.*;
import mindustry.type.*;
import nightscape.generators.ChordaPlanetGenerator;

import static mindustry.content.Items.*;
import static mindustry.content.Planets.*;
import static nightscape.content.NSitems.*;

public class NSplanets {
    public static Planet Chorda;

    public static void load(){
        Chorda = new Planet("Chorda", Planets.sun, 1.1f, 2){{
        generator = new ChordaPlanetGenerator();
        meshLoader = () -> new HexMesh(this, 6);
        cloudMeshLoader = () -> new MultiMesh(
                new HexSkyMesh(this, 23, 0.35f, 0.12f, 5, Color.valueOf("a42283").a(0.55f), 2, 0.45f, 0.9f, 0.38f),
                new HexSkyMesh(this, 33, 0.7f, 0.17f, 5, Color.valueOf("fc81dd").a(0.45f), 2, 0.45f, 1f, 0.41f)
        );
        launchCapacityMultiplier = 0.3f;
        tidalLock = false;
        orbitTime = 240f * 60f * 60f * 60f;
        rotateTime = 0.1f * 60f * 60f * 60f;

        allowLaunchToNumbered = false;
        allowWaves = true;
        allowWaveSimulation = true;
        allowSectorInvasion = false;
        allowLaunchSchematics = false;
        enemyCoreSpawnReplace = true;
        allowLaunchLoadout = false;
        clearSectorOnLose = true;
        iconColor = Color.valueOf("625160");
        atmosphereColor = Color.valueOf("625160");
        defaultCore = NSBother.coreSatellite;
        atmosphereRadIn = 0.02f;
        atmosphereRadOut = 0.35f;
        startSector = 4;
        orbitRadius = 78f;
        accessible = true;
        alwaysUnlocked = true;
        landCloudColor = Color.valueOf("625160");
        itemWhitelist = chordaItems;
        unlockedOnLand.add(NSBother.coreSatellite);
        ruleSetter = r -> {
            r.waveTeam = Team.malis;
            r.placeRangeCheck = false;
            r.showSpawns = true;
            r.fog = true;
            r.coreIncinerates = true;
            r.staticFog = true;
            r.lighting = false;
            r.coreDestroyClear = false;
        };
    }};
    }
}
