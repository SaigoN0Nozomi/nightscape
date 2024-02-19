package nightscape.content;

import arc.graphics.*;
import mindustry.content.Planets;
import mindustry.game.*;
import mindustry.graphics.g3d.*;
import mindustry.type.*;
import nightscape.content.blocks.NSBother;
import nightscape.generators.ChordaPlanetGenerator;

import static nightscape.content.NSitems.*;

public class NSplanets {
    public static Planet Chorda;

    public static void load(){
        Chorda = new Planet("Chorda", Planets.sun, 1.1f, 2){{
        generator = new ChordaPlanetGenerator();
        meshLoader = () -> new HexMesh(this, 5);
        cloudMeshLoader = () -> new MultiMesh(
                new HexSkyMesh(this, 23, 0.35f, 0.13f, 5, Color.valueOf("a42283").a(0.40f), 2, 0.35f, 1f, 0.38f)
        );
        launchCapacityMultiplier = 0.5f;
        tidalLock = false;
        orbitTime = 40f * 60f * 60f * 60f;
        rotateTime = 60f * 60f;

        allowLaunchToNumbered = false;
        allowWaves = true;
        allowWaveSimulation = true;
        allowSectorInvasion = false;
        allowLaunchSchematics = true;
        enemyCoreSpawnReplace = true;
        allowLaunchLoadout = false;
        clearSectorOnLose = true;
        iconColor = Color.valueOf("625160");
        atmosphereColor = Color.valueOf("625160");
        defaultCore = NSBother.coreSatellite;
        atmosphereRadIn = 0.04f;
        atmosphereRadOut = 0.41f;
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
