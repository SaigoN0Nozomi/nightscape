package nightscape.content;

import mindustry.content.Planets;
import mindustry.game.Rules;
import mindustry.type.Planet;
import mindustry.type.SectorPreset;
import mindustry.type.Weather;

import static nightscape.content.NSplanets.Chorda;

public class NSsectors {
    public static SectorPreset
    safeEdge, wasteland, frozenCanyon, bcross, aquarry, sang, mise;

    public static void load(){
        safeEdge = new SectorPreset("safeEdge", Chorda, 4){{
            alwaysUnlocked = true;
            difficulty = 2;
            captureWave = 15;
            startWaveTimeMultiplier = 120/100f;
        }};

        wasteland = new SectorPreset("wasteland", Chorda, 23){{
            difficulty = 4;
            captureWave = 23;
            startWaveTimeMultiplier = 1.3f;
        }};

        frozenCanyon = new SectorPreset("frozenCanyon", Chorda, 12){{
            difficulty = 5;
            captureWave = 6;
            startWaveTimeMultiplier = 2.5f;
        }};

        bcross = new SectorPreset("Bcross", Chorda, 41){{
            difficulty = 4;
            startWaveTimeMultiplier = 1f;
        }};

        aquarry = new SectorPreset("Aquarry", Chorda, 67){{
            difficulty = 7;
            startWaveTimeMultiplier = 1f;
            captureWave = 35;
        }};

        sang = new SectorPreset("sang", Chorda, 69){{
            difficulty = 5;
            startWaveTimeMultiplier = 1f;
            captureWave = 4;
        }};

        mise = new SectorPreset("mise", Chorda, 40){{
            difficulty = 7;
            startWaveTimeMultiplier = 1f;
        }};
    }
}
