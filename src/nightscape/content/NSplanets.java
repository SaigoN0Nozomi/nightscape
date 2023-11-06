package nightscape.content;

import arc.graphics.*;
import mindustry.content.Planets;
import mindustry.game.*;
import mindustry.graphics.g3d.*;
import mindustry.maps.planet.*;
import mindustry.type.*;

import static mindustry.content.Items.*;
import static mindustry.content.Planets.*;
import static nightscape.content.NSitems.*;

public class NSplanets {
    public static Planet Chorda;

    public static void load(){
        Chorda = new Planet("teste", Planets.sun, 0.9f, 2){{
            generator = new ErekirPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 12, 0.35f, 0.14f, 4, Color.valueOf("e4ac5c").a(0.75f), 3, 0.25f, 0.45f, 0.2f),
                    new HexSkyMesh(this, 3, 0.7f, 0.17f, 4, Color.valueOf("eecd74").a(0.75f), 3, 0.25f, 0.6f, 0.3f)
            );
            launchCapacityMultiplier = 0.3f;
            sectorSeed = 3;
            orbitTime = 1 * 60f * 60f * 60f * 60f;
            rotateTime = 4 * 60f * 60f * 60f;

            allowLaunchToNumbered = false;
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = true;
            allowLaunchSchematics = true;
            enemyCoreSpawnReplace = true;
            allowLaunchLoadout = false;
            iconColor = Color.valueOf("625160");
            atmosphereColor = Color.valueOf("625160");
            defaultCore = NSBother.coreSatellite;
            atmosphereRadIn = 0.01f;
            atmosphereRadOut = 0.3f;
            startSector = 1;
            orbitRadius = 42f;
            itemWhitelist = chordaItems;
            alwaysUnlocked = false;
            landCloudColor = Color.valueOf("625160");
            unlockedOnLand.add(NSBother.coreSatellite);
            ruleSetter = r -> {
                r.waveTeam = Team.malis;
                r.placeRangeCheck = false;
                r.showSpawns = true;
                r.fog = true;
                r.coreIncinerates = true;
                r.staticFog = true;
                r.lighting = false;
                r.coreDestroyClear = true;
            };
        }};
    }
}
