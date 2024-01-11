package nightscape;

import arc.util.*;
import mindustry.mod.*;
import nightscape.content.*;
import nightscape.content.blocks.*;
import nightscape.world.UpdEnvContainer;
import nightscape.world.meta.SoundsAlt;

public class nightscape extends Mod{

    public nightscape(){
        Log.info("Loaded NightScape.");
    }

    @Override
    public void loadContent(){
        Log.info("Loading NS content.");

        Log.info("Loading attribute, status, weather and item lists.");
        SoundsAlt.load();
        NSattribute.load();
        NSstatus.load();
        NSweather.load();
        NSitems.load();
        NSLiquids.load();
        Log.info("Loading a lot of units.");
        NSunits.load();
        Log.info("Loading even more blocks.");
        BlockLoad.load();
        Log.info("Planets and tech tree for them.");
        NSplanets.load();
        NSsectors.load();
        CHtechtree.load();
    }
    @Override
    public void init(){
        UpdEnvContainer.load();
    }
}
