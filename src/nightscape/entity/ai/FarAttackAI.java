package nightscape.entity.ai;

import arc.math.Mathf;
import mindustry.ai.Pathfinder;
import mindustry.entities.units.AIController;
import mindustry.gen.Teamc;
import mindustry.world.meta.BlockFlag;

public class FarAttackAI extends AIController {
    @Override
    public void updateMovement(){
        if(target != null) {
            if (target.team() == unit.team) {
                circle(target, unit.type.range * 0.8f);
            }
            if (target.within(unit.x, unit.y, unit.range()*0.95f)){
                unit.lookAt(target);
            } else {
                pathfind(Pathfinder.fieldCore);
                faceTarget();
            }
        }
    }
    @Override
    public Teamc findTarget(float x, float y, float range, boolean air, boolean ground){
        var result = findMainTarget(x, y, range, air, ground);

        return checkTarget(result, x, y, range) ? target(x, y, range, air, ground) : result;
    }

    @Override
    public Teamc findMainTarget(float x, float y, float range, boolean air, boolean ground){
        var core = targetFlag(x, y, BlockFlag.core, true);

        if(core != null && Mathf.within(x, y, core.getX(), core.getY(), range)){
            return core;
        }

        for(var flag : unit.type.targetFlags){
            if(flag == null){
                Teamc result = target(x, y, range, air, ground);
                if(result != null) return result;
            }else if(ground){
                Teamc result = targetFlag(x, y, flag, true);
                if(result != null) return result;
            }
        }

        return core;
    }
}
