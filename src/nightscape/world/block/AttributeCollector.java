package nightscape.world.block;

import arc.Core;
import arc.graphics.Color;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.game.Team;
import mindustry.graphics.Drawf;
import mindustry.world.Tile;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;

import static mindustry.Vars.tilesize;
import static mindustry.Vars.world;

public class AttributeCollector extends AttributeCrafter {
    public int radius = 0;
    public float eff;
    public boolean conf = true;
    public float confCount;
    public Color radColor = Color.white;
    public AttributeCollector(String name){
        super(name);
    }

    @Override
    public void drawOverlay(float x, float y, int rotation) {
        if (radius < 1) return;
        Drawf.dashSquare(radColor, x, y, (size + radius * 2) * tilesize);
    }

    public void setStats(){
        super.setStats();

        stats.remove(Stat.tiles);
        stats.add(Stat.tiles, StatValues.blocks(attribute, floating, boostScale, true, false));
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        displayEfficiency = false;
        super.drawPlace(x, y, rotation, valid);

        drawPlaceText(Core.bundle.format("bar.efficiency", (int)((baseEfficiency + Math.min(maxBoost, eff * boostScale) * 100f ))), x, y, valid);
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return countEfficiency(tile) > 0;
    }

    public float countEfficiency(Tile tile){
        eff = baseEfficiency;
        confCount = 0;
        for(int x = tile.x - radius; x < tile.x + radius + 2; x++){
            for(int y = tile.y - radius; y < tile.y + radius + 2; y++){
                Tile t = world.tile(x, y);
                if (t.block().attributes.get(attribute) > 0) eff = t.block().attributes.get(attribute) + eff;
                else eff = t.floor().attributes.get(attribute) + eff;
                if(conf && t.block() instanceof AttributeCollector) confCount++;
            }
        }
        if(confCount < 1) return Math.min(eff, maxBoost / boostScale);
        else return 0;
    }

    public class AttributeCollectorBuild extends AttributeCrafterBuild{
        @Override
        public void updateTile(){
            if(efficiency > 0){

                progress += getProgressIncrease(craftTime);
                warmup = Mathf.approachDelta(warmup, warmupTarget(), warmupSpeed);

                if(outputLiquids != null){
                    float inc = getProgressIncrease(1f);
                    for(var output : outputLiquids){
                        handleLiquid(this, output.liquid, Math.min(output.amount * inc, liquidCapacity - liquids.get(output.liquid)));
                    }
                }

                if(wasVisible && Mathf.chanceDelta(updateEffectChance)){
                    updateEffect.at(x + Mathf.range(size * 4f), y + Mathf.range(size * 4));
                }
            }else{
                warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
            }

            totalProgress += warmup * Time.delta;

            if(progress >= 1f){
                craft();
            }

            dumpOutputs();
        }

        @Override
        public void onProximityUpdate(){
            super.onProximityUpdate();

            attrsum = eff;
        }
    }
}
