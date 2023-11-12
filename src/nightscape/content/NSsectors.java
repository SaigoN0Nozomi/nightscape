package nightscape.content;

import mindustry.type.SectorPreset;

import static nightscape.content.NSplanets.Chorda;

public class NSsectors {
    public static SectorPreset
    safeEdge, frozenCanyon, deepGap;

    public static void load(){
        safeEdge = new SectorPreset("safeEdge", Chorda, 4){{
            alwaysUnlocked = true;
            difficulty = 1;
            captureWave = 10;
            startWaveTimeMultiplier = 2f;
        }};

        frozenCanyon = new SectorPreset("frozenCanyon", Chorda, 45){{
            alwaysUnlocked = false;
            difficulty = 3;
            captureWave = 15;
            startWaveTimeMultiplier = 2.5f;
        }};

        deepGap = new SectorPreset("deepGap", Chorda, 63){{
            alwaysUnlocked = false;
            difficulty = 5;
            captureWave = 20;
            startWaveTimeMultiplier = 2f;
        }};
    }
}
