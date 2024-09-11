package nightscape.world.block.production.multiExtractor;


import arc.Core;
import mindustry.content.Blocks;
import mindustry.game.Team;
import mindustry.type.Category;
import mindustry.world.Tile;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.HeatCrafter;
import nightscape.world.block.production.HeatCore;

import static mindustry.Vars.world;

// Solid EArth Drill
public class SEaDrill extends AttributeCrafter {
    public float effPerBuff = 0.2f;
    public int radius = 1;
    public SEaDrill(String name){
        super(name);
    }
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return sumAttribute(attribute, tile.x, tile.y) > 0;
    }
    public class SEaDrillBuild extends AttributeCrafterBuild{
        public float countDeMulti(int tx, int ty){
            float multiCount = 0;
            for(int ax = tx - radius; ax < tx + radius + size; ax++){
                for(int ay = ty - radius; ay < ty + radius + size; ay++){
                    Tile t = world.tile(ax, ay);
                    if(t.block() instanceof SEaBuffer){multiCount++;}
                }
            }
            return multiCount;
        }
        public float countDeStabile(int tx, int ty){
            float multiCount = 0;
            for(int ax = tx - radius; ax < tx + radius + size; ax++){
                for(int ay = ty - radius; ay < ty + radius + size; ay++){
                    Tile t = world.tile(ax, ay);
                    if(t.block() instanceof SEaStabilizer){multiCount++;}
                }
            }
            return multiCount;
        }
        @Override
        public void craft(){
            super.craft();
            float fuel = countDeMulti(tile.x - 1, tile.y - 1);
            float stabile = countDeStabile(tile.x - 1, tile.y - 1);
            if(fuel > 1){
                this.damage(Math.max(fuel - stabile, 0) * 5f);
            }
        }
        public void onProximityUpdate(){
            attrsum = Math.min(((countDeMulti(tile.x - 1, tile.y - 1) + 1)* effPerBuff) * Math.min(sumAttribute(attribute, tile.x, tile.y), 1), 1);
        }
        @Override
        public void drawLight(){
        }
    }
}
