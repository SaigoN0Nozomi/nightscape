package nightscape.content;

import nightscape.content.blocks.*;

public class BlockLoad {
    public static void load(){
        NSBdistribution.load();
        NSBenvironment.load();
        NSBother.load();
        NSBpower.load();
        NSBproduction.load();
        NSBturret.load();
        NSBunits.load();
    }
}
