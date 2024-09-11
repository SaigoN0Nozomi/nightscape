package nightscape.world.status;

import arc.math.Mathf;
import arc.util.Strings;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;
import mindustry.world.meta.Stat;

public class DamageFactorsStatus extends StatusEffect {
    public float damageAdd = 0, shootDamage = 0, shieldDamage = 0;
    public Effect damagedFx = Fx.none, shootFx = Fx.none, shieldFx = Fx.none;
    public DamageFactorsStatus(String name) {
        super(name);
    }

    public void setStats(){
        super.setStats();
        if(damageAdd != 0) {
            stats.add(Stat.damage, Strings.autoFixed(damageAdd, 0));
        }
        if(shootDamage != 0) {
            stats.add(Stat.damage, Strings.autoFixed(shootDamage, 0));
        }
        if(shieldDamage != 0) {
            stats.add(Stat.damage, Strings.autoFixed(shieldDamage, 0));
        }
    }

    public void update(Unit unit, float time) {
        if(unit.damaged()){
            unit.damageContinuousPierce(damageAdd);
            if(Mathf.chance(0.1f)){damagedFx.at(unit.x, unit.y);}
        }
        if(unit.isShooting){
            unit.damageContinuousPierce(shootDamage/60);
            if(Mathf.chance(0.1f)){shootFx.at(unit.x, unit.y);}
        }
        if(unit.shield > 0){
            unit.damageContinuousPierce(Math.min(unit.shield, shieldDamage/60));
            if(Mathf.chance(0.1f)){shieldFx.at(unit.x, unit.y);}
        }
    };
}
