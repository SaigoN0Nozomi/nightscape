package nightscape.world.meta;

import arc.audio.Music;
import arc.files.Fi;
import mindustry.Vars;

public class NSMusic {
    /** List of calm ambient music. */
    public static Music god;
    public static void load() {
        god = loadMusic("god");
    }

    private static Music loadMusic(String name) {
        return Vars.tree.loadMusic(name);
    }
}
