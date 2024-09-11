package nightscape.entity.abilities;

import mindustry.entities.Units;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;
import nightscape.content.NSweather;
import nightscape.world.meta.NSMusic;
import nightscape.world.meta.SoundsAlt;

public class GODAbility extends Ability {
    public float timer = 9999999;
    @Override
    public void update(Unit unit) {
        timer++;
        if(timer > 990000) {
            NSMusic.god.pause(true);
            NSweather.ozoneStorm.create(1, 990000);
            timer = 0;
            NSMusic.god.play();
            NSMusic.god.setLooping(true);
        }
        var target = Units.closestEnemy(unit.team, unit.x, unit.y, 60, u -> !u.dead());
        var targetBuild = Units.findEnemyTile(unit.team, unit.x, unit.y, 60, t -> !t.dead);

        if(target != null){
            target.damage(9999);
        }
        else if (targetBuild != null){
            targetBuild.damage(9999);
        }
    }
}
