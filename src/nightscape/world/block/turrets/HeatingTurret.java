package nightscape.world.block.turrets;

import arc.Core;
import arc.audio.Sound;
import arc.math.Mathf;
import arc.util.Strings;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.ControlBlock;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.meta.StatUnit;
import nightscape.world.meta.NSStat;

import static mindustry.Vars.tilesize;

public class HeatingTurret extends ItemTurret {
    public float heatingMax, heatingLose, heatingGain;
    public Sound coolingSound = Sounds.none;
    public Effect coolingFx = Fx.steam;
    public HeatingTurret(String name){
        super(name);
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(NSStat.coolTime, Strings.autoFixed(heatingMax / heatingLose / 60, 0), StatUnit.seconds);
        stats.add(NSStat.heatTime, Strings.autoFixed(heatingMax / heatingGain / 60, 0), StatUnit.seconds);
    }
    @Override
    public void setBars() {
        super.setBars();
        addBar("heating", (HeatingTurretBuild entity) ->
            new Bar(() ->
                    Core.bundle.format("bar.heatshoot", (int)(Math.min(entity.heating / heatingMax * 100, 100))),
                    () -> Pal.lightOrange,
                    () -> entity.heating / heatingMax
            )
        );
    }

    public class HeatingTurretBuild extends ItemTurretBuild implements ControlBlock {
        public float heating;
        public boolean cooling;
        public void updateTile(){
            if(this.isShooting() && !cooling){
                heating += heatingGain;
            }
            else {
                heating = Math.max(heating - heatingLose, 0);
            }
            if(heating <= 0){
                cooling = false;
            }
            if(heating >= heatingMax || cooling){
                this.reloadCounter = 0;
                if(!cooling){
                    coolingSound.at(this.x, this.y);
                }
                cooling = true;
            }
            if(cooling && Mathf.chance(0.2f)){
                coolingFx.at(this.x + Mathf.random(size * tilesize) - size * tilesize/2f, this.y + Mathf.random(size * tilesize) - size * tilesize/2f);
            }
            super.updateTile();
        }
    }
}
