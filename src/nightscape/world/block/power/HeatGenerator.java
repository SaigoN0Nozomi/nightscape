package nightscape.world.block.power;

import arc.Core;
import arc.Events;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.game.EventType;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class HeatGenerator extends ConsumeGenerator {
    public float heatReq = 0;
    public float minEff = 0;
    public HeatGenerator(String name){
        super(name);
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.input, heatReq, StatUnit.heatUnits);
    }
    @Override
    public void setBars(){
        super.setBars();

        addBar("heat", (HeatGeneratorBuild e) ->
                new Bar(() -> Core.bundle.format("bar.heatpercent", (int)(e.heatAmount + 0.01f), (int)(e.getHeatAmount() * 100)), () -> Pal.lightOrange, () -> e.heatAmount / heatReq));
    }
    public class HeatGeneratorBuild extends ConsumeGeneratorBuild{
        public float[] sideHeat = new float[4];
        public float heatAmount = 0;
        @Override
        public void updateTile() {
            heatAmount = calculateHeat(sideHeat);

            boolean valid = efficiency > 0;

            warmup = Mathf.lerpDelta(warmup, valid ? 1f : 0f, warmupSpeed);

            productionEfficiency = efficiency * efficiencyMultiplier * Math.min(Math.max(heatAmount/heatReq, minEff), 1);
            totalTime += warmup * Time.delta;

            if(valid && Mathf.chanceDelta(effectChance)){
                generateEffect.at(x + Mathf.range(generateEffectRange), y + Mathf.range(generateEffectRange));
            }

            if(hasItems && valid && generateTime <= 0f){
                consume();
                consumeEffect.at(x + Mathf.range(generateEffectRange), y + Mathf.range(generateEffectRange));
                generateTime = 1f;
            }

            if(outputLiquid != null){
                float added = Math.min(productionEfficiency * delta() * outputLiquid.amount, liquidCapacity - liquids.get(outputLiquid.liquid));
                liquids.add(outputLiquid.liquid, added);
                dumpLiquid(outputLiquid.liquid);

                if(explodeOnFull && liquids.get(outputLiquid.liquid) >= liquidCapacity - 0.01f){
                    kill();
                    Events.fire(new EventType.GeneratorPressureExplodeEvent(this));
                }
            }

            generateTime -= delta() / itemDuration;
        }

        public float getHeatAmount(){
            if((heatAmount / heatReq) <= 1){
                return (heatAmount / heatReq);
            }
            else return 1;
        }
    }
}
