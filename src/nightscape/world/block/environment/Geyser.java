package nightscape.world.block.environment;

import arc.math.Mathf;
import arc.util.Interval;
import arc.util.Time;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.bullet.BulletType;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.world.Build;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.StaticWall;

import static arc.math.Mathf.rand;

public class Geyser extends StaticWall implements UpdateEnvironment<Interval>{
    public BulletType bullet;
    public int gTime, chargeTime;
    public int charge = 0, charge2 = 0;
    public Geyser(String name){
        super(name);
    }

    @Override
    public Interval getDefaultValue(){
        Interval interval = new Interval();
        interval.getTimes()[0] = Time.time - Mathf.random() * Time.toSeconds;
        return interval;
    }

    public void updateTile(Tile tile, Interval data){
        if(!Vars.state.isPaused() && !Vars.state.isEditor()) {
            charge++;
            if (charge >= chargeTime) {
                charge2++;
                if (charge2 <= gTime) {
                    if (charge2 % 3 == 0) {
                        bullet.create(null, Team.neoplastic, tile.worldx(), tile.worldy(), Mathf.random(0, 360), Mathf.random(0.1f, 1));
                    }
                } else {
                    charge = 0;
                    charge2 = 0;
                    chargeTime += Mathf.random(1000);
                }
            }
        }
    }
}