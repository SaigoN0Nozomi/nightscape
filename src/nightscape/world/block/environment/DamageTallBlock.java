package nightscape.world.block.environment;

import arc.math.Mathf;
import arc.util.Interval;
import arc.util.Time;
import mindustry.entities.Units;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.TallBlock;

public class DamageTallBlock extends TallBlock implements UpdateEnvironment<Interval> {
    public float damageRange = 8;
    public float damage = 1;
    public DamageTallBlock(String name){
        super(name);
    }
    @Override
    public Interval getDefaultValue(){
        Interval interval = new Interval();
        interval.getTimes()[0] = Time.time - Mathf.random() * Time.toSeconds;
        return interval;
    }
    public void updateTile(Tile tile, Interval data){
        if(!data.get(Time.toSeconds / 8)) return;
        Units.nearby(null, tile.worldx(), tile.worldy(), damageRange, u -> {
            if(!u.isFlying()){
                u.damage(damage);
            }
        });
    }
}
