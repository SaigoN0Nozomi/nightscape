package nightscape.content;

import mindustry.content.Planets;
import mindustry.type.Planet;
import mindustry.type.SectorPreset;

import static nightscape.content.NSplanets.Chorda;

public class NSsectors {
    public static SectorPreset
    safeEdge, iceCrater, shieldValley, purplePlateau, frozenFault, passage, badelaire;

    public static void load(){
        safeEdge = new SectorPreset("safeEdge", Chorda, 4){{
            alwaysUnlocked = true;
            difficulty = 2;
            captureWave = 15;
            startWaveTimeMultiplier = 2f;
        }};

        iceCrater = new SectorPreset("IceCrater", Chorda, 50){{
            difficulty = 3;
            captureWave = 20;
            startWaveTimeMultiplier = 2.5f;
        }};

        shieldValley = new SectorPreset("shieldValley", Chorda, 61){{
            difficulty = 5;
        }};

        purplePlateau = new SectorPreset("purplePlateau", Chorda, 38){{
            difficulty = 4;
            captureWave = 25;
        }};

        frozenFault = new SectorPreset("frozenFault", Chorda, 45){{
            difficulty = 3;
        }};

        passage = new SectorPreset("passage", Chorda, 78){{
            difficulty = 4;
            captureWave = 20;
        }};

        badelaire = new SectorPreset("badelaire", Chorda, 12){{
            difficulty = 6;
            startWaveTimeMultiplier = 1.5f;
        }};
    }
}
