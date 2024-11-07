package nightscape.content;

import arc.graphics.*;
import mindustry.game.*;
import mindustry.graphics.g3d.*;
import mindustry.type.*;
import nightscape.content.blocks.NSBother;
import nightscape.generators.ChordaPlanetGenerator;
import nightscape.generators.PrymaPlanetGenerator;
import nightscape.generators.SatOneGenerator;
import nightscape.generators.SatTwoGenerator;
import nightscape.world.meta.NSTeams;

import static nightscape.content.NSitems.*;

public class NSplanets {
    public static Planet Decim, Chorda, Pryma,
    //sat
    Foxi, Lapsi;

    public static void load(){
        Decim = new Planet("Decim", null, 6f){{
            bloom = true;
            accessible = false;

            meshLoader = () -> new SunMesh(
                    this, 4,
                    5, 0.3, 1.7, 1.2, 1,
                    1.1f,
                    Color.valueOf("f7a078"),
                    Color.valueOf("fa704a"),
                    Color.valueOf("ee442c"),
                    Color.valueOf("9f2e19"),
                    Color.valueOf("962a21"),
                    Color.valueOf("c63622")
            );
        }};

        Chorda = new Planet("Chorda", Decim, 1.1f, 2){{
            generator = new ChordaPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 5);
            cloudMeshLoader = () -> new MultiMesh(
                new HexSkyMesh(this, 23, 0.35f, 0.11f, 5, Color.valueOf("a42283").a(0.30f), 2, 0.35f, 1f, 0.38f),
                new HexSkyMesh(this, 24, 0.45f, 0.13f, 5, Color.valueOf("c442a3").a(0.60f), 2, 0.35f, 1f, 0.38f)
            );
            launchCapacityMultiplier = 0.5f;
            tidalLock = false;
            orbitTime = 40f * 60f * 60f * 60f;
            rotateTime = 60f * 60f;

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
            atmosphereRadIn = 0.04f;
            atmosphereRadOut = 0.41f;
            lightSrcTo = 0.42f;
            lightDstFrom = 0.12f;
            startSector = 4;
            orbitRadius = 56f;
            accessible = true;
            alwaysUnlocked = true;
            landCloudColor = Color.valueOf("625160");
            itemWhitelist = chordaItems;
            unlockedOnLand.add(NSBother.coreSatellite);
            ruleSetter = r -> {
                r.waveTeam = NSTeams.sanc;
                r.placeRangeCheck = false;
                r.showSpawns = true;
                r.fog = true;
                r.coreIncinerates = true;
                r.staticFog = true;
                r.staticColor = Color.valueOf("000000BB");
                r.lighting = false;
                r.coreDestroyClear = false;
                r.weather.add(new Weather.WeatherEntry(NSweather.ozoneRain, 5 * 60 * 60, 7 * 60 * 60, 20 * 60, 60 * 60));
            };
        }};

        Foxi = new Planet("Fox1", Chorda, 0.25f, 3){{
            generator = new SatOneGenerator();
            meshLoader = () -> new HexMesh(this, 3);
            launchCapacityMultiplier = 0.5f;
            accessible = false;
            tidalLock = true;
            orbitTime = 10f * 60f * 60f;
            orbitRadius = 3.3f;
            atmosphereRadIn = 0f;
            atmosphereRadOut = 0f;
            lightSrcTo = 0f;
            lightDstFrom = 0f;
        }};
        Lapsi = new Planet("Laps1", Chorda, 0.2f, 3){{
            generator = new SatTwoGenerator();
            meshLoader = () -> new HexMesh(this, 3);
            launchCapacityMultiplier = 0.5f;
            accessible = false;
            tidalLock = true;
            orbitTime = 30f * 60f * 60f;
            orbitRadius = 2.2f;
            atmosphereRadIn = 0f;
            atmosphereRadOut = 0f;
            lightSrcTo = 0f;
            lightDstFrom = 0f;
        }};
        Pryma = new Planet("Pryma", Decim, 1.15f, 2){{
            generator = new PrymaPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 5);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 25, 0.35f, 0.12f, 5, Color.valueOf("ff9f87").a(0.14f), 2, 0.35f, 1f, 0.38f),
                    new HexSkyMesh(this, 26, 0.40f, 0.14f, 5, Color.valueOf("ff9f87").a(0.24f), 2, 0.35f, 1f, 0.38f),
                    new HexSkyMesh(this, 27, 0.45f, 0.15f, 5, Color.valueOf("ffb6ab").a(0.35f), 2, 0.35f, 1f, 0.38f)
            );
            launchCapacityMultiplier = 0.2f;
            tidalLock = false;
            orbitTime = 5f * 60f * 60f * 60f;
            rotateTime = 20f * 60f;
            orbitRadius = 28f;

            allowLaunchToNumbered = false;
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = false;
            allowLaunchSchematics = false;
            enemyCoreSpawnReplace = true;
            allowLaunchLoadout = false;
            clearSectorOnLose = true;
            iconColor = Color.valueOf("ff2819");
            atmosphereColor = Color.valueOf("ff2819");
            defaultCore = NSBother.coreSatellite;
            atmosphereRadIn = 0f;
            atmosphereRadOut = 0.34f;
            startSector = 4;
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
                r.weather.add(
                        new Weather.WeatherEntry(NSweather.storm, 12 * 60 * 60, 18 * 60 * 60, 2 * 60 * 60, 4 * 60 * 60),
                        new Weather.WeatherEntry(NSweather.storm, 20 * 60 * 60, 25 * 60 * 60, 4 * 60 * 60, 8 * 60 * 60)
                );
                r.staticFog = true;
                r.staticColor = Color.valueOf("000000BB");
                r.lighting = false;
                r.coreDestroyClear = false;
            };
        }};
    }
}
