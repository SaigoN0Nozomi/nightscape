package nightscape;

import arc.util.*;
import mindustry.mod.*;
import nightscape.content.*;
import nightscape.world.UpdEnvContainer;
import nightscape.world.meta.NSTeams;
import nightscape.world.meta.SoundsAlt;

public class nightscape extends Mod{

    public nightscape(){
        Log.info("Loaded NightScape.");
    }

    @Override
    public void loadContent(){

        Log.info("Loading NS content.");

        Log.info("Loading attribute, status, weather and item lists.");
        NSTeams.load();
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
        //ui.editor.shown(this::addEditorTeams);
    }
    /*
    Эсли анюк всё же пустит зелёных в расход, то это будет временная затычка

    public void addEditorTeams() {
        WidgetGroup teambuttons = (WidgetGroup) ui.editor.getChildren().get(0);
        teambuttons = (WidgetGroup)teambuttons.getChildren().get(0);
        teambuttons = (WidgetGroup)teambuttons.getChildren().get(0);

        ((Table)teambuttons).row();

        Team team = Team.get(109);

        ImageButton button = new ImageButton(Tex.whiteui, Styles.clearNoneTogglei);
        button.margin(4f);
        button.getImageCell().grow();
        button.getStyle().imageUpColor = team.color;
        button.clicked(() -> editor.drawTeam = team);
        button.update(() -> button.setChecked(editor.drawTeam == team));

        ((Table)teambuttons).add(button);
    }
     */
}
