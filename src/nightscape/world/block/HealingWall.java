package nightscape.world.block;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import nightscape.content.effects.blockFx;

public class HealingWall extends Wall{
    public float healPercent = 0;
    public int reload = 30;
    public Color healColor = Color.valueOf("84f49144");



    public HealingWall(String name){
        super(name);
        update = true;
        suppressable = true;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.repairSpeed, (int)(health * healPercent / 100 * 60 / reload), StatUnit.perSecond);
    }

    public class HealingWallBuild extends WallBuild{
        public int charge;
        public void updateTile(){

            boolean canHeal = !checkSuppression();

            charge++;
            if(charge >= reload && canHeal) {
                heal(health * healPercent / 100);
                blockFx.healWall.at(x, y, size, healColor, block);
                charge = 0;
            }
        }
    }
}
