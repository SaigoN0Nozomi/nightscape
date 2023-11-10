package nightscape.content;

import mindustry.Vars;
import mindustry.game.Schematic;
import mindustry.game.Schematics;

import java.io.IOException;

public class loadouts{
    public static Schematic
            core;

    public static void load(){
        core = loadSchem("coreSatellite");
    }

    private static Schematic loadSchem(String name) {
        Schematic s;
        try {
            s = Schematics.read(Vars.tree.get("schematics/" + name + ".msch"));
        } catch (IOException e) {
            s = null;
            e.printStackTrace();
        }
        return s;
    }

    private static Schematic loadBase64(String b64) {
        return Schematics.readBase64(b64);
    }
}
