package nightscape.world.block;

import arc.Core;
import arc.math.Mathf;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.MendProjector;
import mindustry.world.meta.Env;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

import static mindustry.Vars.*;

public class BetterMend extends MendProjector {

    public float healAmount = 10f;
    public float maxHeat, hRange;
    public float heatRequirement = 1f;
    public BetterMend(String name){
        super(name);
        solid = true;
        update = true;
        hasPower = true;
        hasItems = true;
        emitLight = true;
        lightRadius = 35f;
        suppressable = true;
        envEnabled |= Env.space;
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("heat", (BetterMendBuild entity) ->
                new Bar(() ->
                        Core.bundle.format("bar.heatpercent", (int)(entity.heatAmount + 0.01f), (int)(entity.getHeatAmount() * 100)),
                        () -> Pal.lightOrange,
                        () -> entity.heatAmount / heatRequirement));
    }
    @Override
    public void setStats(){
        stats.timePeriod = useTime;
        super.setStats();
        stats.add(Stat.repairSpeed, (int)(healAmount / reload * 60f), StatUnit.perSecond);
        stats.add(Stat.input, heatRequirement, StatUnit.heatUnits);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range + hRange * maxHeat, baseColor.a(0.2f));
        baseColor.a(1);
    }
    public class BetterMendBuild extends MendBuild{
        public float[] sideHeat = new float[4];
        public float heatAmount = 0f;
        @Override
        public void drawSelect(){
            float realRange = range + getHeatAmount() * hRange;

            indexer.eachBlock(this, realRange, other -> true, other -> Drawf.selected(other, Tmp.c1.set(baseColor).a(Mathf.absin(4f, 1f))));

            Drawf.dashCircle(x, y, realRange, baseColor);
        }
        public void updateTile(){
            heatAmount = calculateHeat(sideHeat);

            boolean canHeal = !checkSuppression();

            smoothEfficiency = Mathf.lerpDelta(smoothEfficiency, efficiency, 0.08f);
            heat = Mathf.lerpDelta(heat, efficiency > 0 && canHeal ? 1f : 0f, 0.08f);
            charge += heat * delta() * getHeatAmount();

            phaseHeat = Mathf.lerpDelta(phaseHeat, optionalEfficiency, 0.1f);

            if(optionalEfficiency > 0 && timer(timerUse, useTime) && canHeal){
                consume();
            }

            if(charge >= reload && canHeal){
                float realRange = range + getHeatAmount() * hRange;
                charge = 0f;

                indexer.eachBlock(this, realRange, b -> b.damaged() && !b.isHealSuppressed(), other -> {
                    other.heal(healAmount * efficiency + other.maxHealth * healPercent / 100);
                    other.recentlyHealed();
                    Fx.healBlockFull.at(other.x, other.y, other.block.size, baseColor, other.block);
                });
            }
        }

        public float getHeatAmount(){
            if((heatAmount / heatRequirement) <= maxHeat) {
                return (heatAmount / heatRequirement);
            }
            else return maxHeat;
        }
    }
}
