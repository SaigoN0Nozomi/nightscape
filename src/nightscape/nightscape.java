package nightscape;

import arc.util.*;
import mindustry.mod.*;
import nightscape.content.*;

public class nightscape extends Mod{

    public nightscape(){
        Log.info("Loaded NightScape constructor.");
    }

    @Override
    public void loadContent(){
        Log.info("Loading NS content.");

        Log.info("Loading attribute, status, weather and item lists.");
        NSattribute.load();
        NSstatus.load();
        NSweather.load();
        NSitems.load();
        Log.info("Loading a lot of units.");
        NSunits.load();
        Log.info("Loading even more blocks.");
        NSBenvironment.load();
        NSBdistribution.load();
        NSBproduction.load();
        NSBturret.load();
        NSBother.load();
        Log.info("Planets and tech tree for them.");
        NSplanet.load();
        CHtechtree.load();
    }
}
