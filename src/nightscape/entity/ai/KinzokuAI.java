package nightscape.entity.ai;

import arc.math.Mathf;
import mindustry.Vars;
import mindustry.entities.Units;
import mindustry.entities.units.AIController;
import mindustry.gen.Teamc;
import nightscape.content.NSunits;

public class KinzokuAI  extends AIController {
    @Override
    public void updateMovement() {
        unloadPayloads();

        if(target != null){
            moveTo(target, unit.type.range * 0.8f, 1, true, null);
            unit.lookAt(target);
        }
    }

    @Override
    public void updateTargeting(){
        if(retarget()) target = findTarget(unit.x, unit.y, unit.type.range * 3, true, true);
    }

    @Override
    public Teamc findTarget(float x, float y, float range, boolean air, boolean ground){
        var result = Units.closest(unit.team, x, y, range, u -> !u.dead() && u.maxHealth > u.health && u.type != unit.type);
        if(result != null) return result;

        result = Units.closest(unit.team, x, y, range, u -> !u.dead() && u.type == NSunits.suta);
        if(result != null) return result;

        var core = unit.closestCore();
        if(core != null) return core;

        if(Vars.state.rules.waves && unit.team == Vars.state.rules.waveTeam){
            return unit.closestEnemyCore();
        }

        return null;
    }
}
