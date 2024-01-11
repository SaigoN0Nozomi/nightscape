package nightscape.world.block.production;

import arc.Core;
import arc.math.Mathf;
import arc.util.Strings;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.production.Drill;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class NSDrill extends Drill {
    public float heatReq = 0;
    public NSDrill(String name){
        super(name);
    }
    @Override
    public void setStats(){
        super.setStats();

        if(heatReq != 0){
            stats.add(Stat.input, heatReq, StatUnit.heatUnits);
        }
    }
    @Override
    public void setBars(){
        super.setBars();

        removeBar("drillspeed");
        addBar("drillspeed", (NSDrillBuild e) ->
            new Bar(() -> Core.bundle.format("bar.drillspeed", Strings.fixed(e.lastDrillSpeed * 60 * e.timeScale() * Math.min(1, e.heatAmount / heatReq), 2)), () -> Pal.ammo, () -> e.warmup));
        addBar("heat", (NSDrillBuild e) ->
            new Bar(() -> Core.bundle.format("bar.heatpercent", (int)(e.heatAmount + 0.01f), (int)(e.getHeatAmount() * 100)), () -> Pal.lightOrange, () -> e.heatAmount / heatReq));
    }
    public class NSDrillBuild extends DrillBuild{
        public float[] sideHeat = new float[4];
        public float heatAmount = 0;

        @Override
        public void updateTile(){
            heatAmount = calculateHeat(sideHeat);

            if(timer(timerDump, dumpTime)){
                dump(dominantItem != null && items.has(dominantItem) ? dominantItem : null);
            }

            if(dominantItem == null){
                return;
            }

            timeDrilled += warmup * delta();

            float delay = getDrillTime(dominantItem);

            if(items.total() < itemCapacity && dominantItems > 0 && efficiency > 0){
                float speed = Mathf.lerp(1f, liquidBoostIntensity, optionalEfficiency) * efficiency * Math.min(1, heatAmount / heatReq);

                lastDrillSpeed = (speed * dominantItems * warmup) / delay;
                warmup = Mathf.approachDelta(warmup, speed, warmupSpeed);
                progress += delta() * dominantItems * speed * warmup * Math.min(1, heatAmount / heatReq);

                if(Mathf.chanceDelta(updateEffectChance * warmup))
                    updateEffect.at(x + Mathf.range(size * 2f), y + Mathf.range(size * 2f));
            }else{
                lastDrillSpeed = 0f;
                warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
                return;
            }

            if(dominantItems > 0 && progress >= delay && items.total() < itemCapacity){
                offload(dominantItem);

                progress %= delay;

                if(wasVisible && Mathf.chanceDelta(updateEffectChance * warmup)) drillEffect.at(x + Mathf.range(drillEffectRnd), y + Mathf.range(drillEffectRnd), dominantItem.color);
            }
        }

        public float getHeatAmount(){
            if((heatAmount / heatReq) <= 1){
                return (heatAmount / heatReq);
            }
            else return 1;
        }
    }
}
