package nightscape.world.types.abilities;

import arc.flabel.effects.ShakeEffect;
import arc.scene.ui.layout.Table;
import arc.util.Strings;
import arc.util.Time;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import nightscape.content.effects.unitFx;
import nightscape.world.meta.NSStatUnit;

import static mindustry.world.meta.Stat.damage;

public class MinigunAbility extends Ability {
    public float heatMax, heatDamage;
    public float heatReloadMultiplier;
    public float heat, dr;
    public void addStats(Table t) {
        t.add("[lightgray]" + Stat.reloadMultiplier.localized() + ": [white]" + NSStatUnit.upto.localized() + " " + Strings.autoFixed(heatReloadMultiplier*1.5f, 2));
        t.row();
        t.add("[lightgray]" + damage.localized() + ": [white]" + Strings.autoFixed(heatDamage/(50/60f), 2) + " " + StatUnit.perSecond.localized());
        t.row();
        t.add(StatusEffects.burning.emoji() + " " + StatusEffects.burning.localizedName);
    }
    @Override
    public void update(Unit unit) {
        if (heat < heatMax) {
            if (heat < heatMax / 2) unit.reloadMultiplier = 1;
            else unit.reloadMultiplier = heatReloadMultiplier*0.75f;
        } else {
            if(dr >= 50) {
                unit.damage(heatDamage);
                unit.apply(StatusEffects.burning, 100);
                dr = 0;
                unitFx.OVERheat.at(unit.x, unit.y);
            }
            else dr +=1;
            if (heat < heatMax*2) unit.reloadMultiplier = heatReloadMultiplier;
            else unit.reloadMultiplier = heatReloadMultiplier*1.5f;
        }
        if(unit.isShooting && heat < heatMax*3) heat +=1;
        else heat -= 1;
    }
}
