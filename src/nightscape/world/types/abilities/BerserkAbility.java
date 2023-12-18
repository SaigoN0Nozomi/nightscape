package nightscape.world.types.abilities;

import arc.scene.ui.layout.Table;
import arc.util.Strings;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import nightscape.world.meta.NSStatUnit;

public class BerserkAbility extends Ability {
    public float accMultiplier = 0;
    public float healMultiplier = 0;

    public void addStats(Table t) {
        if (accMultiplier != 0) {
            t.add("[lightgray]" + Stat.reloadMultiplier.localized() + ": [white]" + NSStatUnit.upto.localized() + " " + Strings.autoFixed(1 + accMultiplier, 2));
            t.row();
        }
        if (healMultiplier != 0) {
            t.add("[lightgray]" + Stat.healing.localized() + ": [white]" + NSStatUnit.upto.localized() + " " + Strings.autoFixed(healMultiplier * 60, 2) + " " + StatUnit.perSecond.localized());
        }
    }

    @Override
    public void update(Unit unit) {
        if (accMultiplier != 0) {
            unit.reloadMultiplier = 1 + 1 * accMultiplier - unit.health / unit.maxHealth * accMultiplier;
        }
        if (healMultiplier != 0) {
            unit.heal(1 * healMultiplier - unit.health / unit.maxHealth * healMultiplier);
        }
    }
}
