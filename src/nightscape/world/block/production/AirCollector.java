package nightscape.world.block.production;

import arc.Core;
import arc.graphics.Color;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.content.Blocks;
import mindustry.game.Team;
import mindustry.graphics.Drawf;
import mindustry.type.Category;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawRegion;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;
import nightscape.content.NSattribute;

import static mindustry.Vars.tilesize;
import static mindustry.Vars.world;

public class AirCollector extends AttributeCrafter {
    public int radius = 0;
    public float eff, effPerAir = 0.1f;
    public float confCount, rotSpeed;
    public Color radColor = Color.white;
    public AirCollector(String name){
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
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        displayEfficiency = false;
        super.drawPlace(x, y, rotation, valid);

        drawPlaceText(Core.bundle.format("bar.efficiency", (int)((baseEfficiency + Math.min(maxBoost, countEfficiency(x, y) * boostScale) * 100f ))), x, y, valid);
    }
    public float countEfficiency(int tx, int ty){
        eff = baseEfficiency;
        confCount = 0;
        for(int ax = tx - radius; ax < tx + radius + size; ax++){
            for(int ay = ty - radius; ay < ty + radius + size; ay++){
                Tile t = world.tile(ax, ay);
                if(t == null){return 0;}
                if(t.block() == Blocks.air || t.block().category.equals(Category.distribution) || t.block().category.equals(Category.liquid)) eff = eff + effPerAir;
            }
        }
        if(confCount < 1) return Math.min(eff, maxBoost / boostScale);
        else return 0;
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return countEfficiency(tile.x, tile.y) > 0;
    }

    public class AirCollectorBuild extends AttributeCrafterBuild{
        @Override
        public void updateTile(){
            if(efficiency > 0){

                progress += getProgressIncrease(craftTime);
                warmup = Mathf.approachDelta(warmup, warmupTarget(), warmupSpeed);
                rotSpeed = 10 * warmup;

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
            attrsum = countEfficiency(tile.x, tile.y);
        }
    }
}
