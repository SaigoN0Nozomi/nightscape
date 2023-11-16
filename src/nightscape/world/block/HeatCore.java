package nightscape.world;

import arc.Core;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.util.Strings;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.*;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import nightscape.world.meta.NSStatUnit;

public class HeatCore extends HeatProducer {
    public float perEnOutput = 1f;
    public float heatMin = 1;
    public float heatMax = 3;
    public float step = 0.25f;

    public HeatCore(String name){
        super(name);

        drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
        rotateDraw = false;
        rotate = true;
        canOverdrive = false;
        drawArrow = true;
        configurable = true;
        hasPower = true;
        hasItems = false;
        config(Float.class,(HeatCoreBuild build, Float val)->{build.heatOut = val;});
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.remove(Stat.powerUse);
        stats.remove(Stat.output);
        stats.add(Stat.input, perEnOutput * 60f, NSStatUnit.heatPerEn);
        stats.add(Stat.output, heatMax, NSStatUnit.heatMax);
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("heat", (HeatCoreBuild entity) -> new Bar("bar.heat", Pal.lightOrange, () -> entity.heat / entity.heatOut));
    }

    public class HeatCoreBuild extends HeatProducerBuild{
        public float heatOut = (heatMax + heatMin) / 2;
        @Override
        public void updateTile(){
            heatOutput = heatOut;
            consumePower(perEnOutput * heatOut);
            super.updateTile();
        }
        @Override
        public void buildConfiguration(Table table) {
            super.buildConfiguration(table);

            table.label(()-> Core.bundle.format("bar.nscape-heat",
                    Strings.fixed(heatOut, 1)));

            table.row();
            table.slider(heatMin, heatMax, step, heatOut, true, this::configure);
        }
    }
}
