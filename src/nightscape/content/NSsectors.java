package nightscape.content;

import mindustry.type.SectorPreset;

import static nightscape.content.NSplanets.Chorda;

public class NSsectors {
    public static SectorPreset
    safeEdge, iceCrater, shieldValley, purplePlateau;

    public static void load(){
        safeEdge = new SectorPreset("safeEdge", Chorda, 4){{
            alwaysUnlocked = true;
            difficulty = 2;
            captureWave = 15;
            startWaveTimeMultiplier = 2f;
        }};

        iceCrater = new SectorPreset("IceCrater", Chorda, 33){{
            difficulty = 3;
            captureWave = 20;
            startWaveTimeMultiplier = 2.5f;
        }};

        shieldValley = new SectorPreset("shieldValley", Chorda, 63){{
            difficulty = 5;
            startWaveTimeMultiplier = 1f;
        }};

        purplePlateau = new SectorPreset("purplePlateau", Chorda, 69){{
            difficulty = 4;
            startWaveTimeMultiplier = 1f;
            captureWave = 25;
        }};
    }
}
