package nightscape;

import arc.scene.ui.ImageButton;
import arc.scene.ui.layout.Table;
import arc.scene.ui.layout.WidgetGroup;
import arc.util.*;
import mindustry.game.Team;
import mindustry.gen.Tex;
import mindustry.mod.*;
import mindustry.ui.Styles;
import nightscape.content.*;
import nightscape.content.OZONEGOD.OZONEGOD;
import nightscape.world.UpdEnvContainer;
import nightscape.world.meta.NSMusic;
import nightscape.world.meta.NSTeams;
import nightscape.world.meta.SoundsAlt;

import static mindustry.Vars.editor;
import static mindustry.Vars.ui;

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
        NSMusic.load();
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
        OZONEGOD.load();
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
