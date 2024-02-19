package nightscape.entity.ai;

import arc.math.Mathf;
import mindustry.Vars;
import mindustry.entities.Units;
import mindustry.entities.units.AIController;
import mindustry.gen.Teamc;

public class SemiDefenderAI extends AIController {
    @Override
    public void updateMovement() {
        unloadPayloads();
        if(target != null) {
            if (target.team() == unit.team) {
                circle(target, unit.type.range * 0.8f);
            }
            else moveTo(target, unit.type.range * 0.8f); unit.lookAt(target);
        }
    }

    @Override
    public void updateTargeting(){
        var enemy = target(unit.x, unit.y, unit.type.range, true, true);
        if(enemy != null){ target = enemy; updateWeapons();}
        else target = findTarget(unit.x, unit.y, unit.type.range * 2, true, true);
    }

    @Override
    public Teamc findTarget(float x, float y, float range, boolean air, boolean ground){
        var result = Units.closest(unit.team, x, y, 10000, u -> !u.dead() && u.type != unit.type && u.targetable(unit.team) && u.type.playerControllable,
                (u, tx, ty) -> -u.maxHealth + Mathf.dst2(u.x, u.y, tx, ty) / 6400f);
        if(result != null) return result;

        var core = unit.closestCore();
        if(core != null) return core;

        if(Vars.state.rules.waves && unit.team == Vars.state.rules.waveTeam){
            return unit.closestEnemyCore();
        }

        return null;
    }
}
