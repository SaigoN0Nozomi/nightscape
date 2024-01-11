package nightscape.world.types.abilities;

import arc.scene.ui.layout.Table;
import arc.util.Strings;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import nightscape.world.meta.NSStatUnit;

public class DeathWishAbility extends Ability {
    public float radius, damage;
    public Effect deathWish;
    public void addStats(Table t) {
        t.add("[lightgray]" + Stat.damage.localized() + ": [white]" + Strings.autoFixed(damage, 2));
        t.row();
        t.add("[lightgray]" + Stat.range.localized() + ": [white]" + Strings.autoFixed(radius / 8, 2) + " " + StatUnit.blocks.localized());
    }
    public void death(Unit unit){
        Damage.damage(unit.x, unit.y, radius, damage);
        deathWish.at(unit.x, unit.y);
    }
}
