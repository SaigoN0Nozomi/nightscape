package nightscape.entity.abilities;

import arc.scene.ui.layout.Table;
import arc.util.Strings;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.abilities.Ability;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Unit;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import nightscape.entity.FieldBulletType;

public class DeathWishAbility extends Ability {
    public float radius, damage;
    public BulletType Wish;
    public Effect deathWish;
    public void addStats(Table t) {
        t.add("[lightgray]" + Stat.damage.localized() + ": [white]" + Strings.autoFixed(damage*10, 2));
        t.row();
        t.add("[lightgray]" + Stat.damage.localized() + ": [white]" + Strings.autoFixed(damage*20, 2) + StatUnit.perSecond.localized());
        t.row();
        t.add("[lightgray]" + Stat.range.localized() + ": [white]" + Strings.autoFixed(radius / 8, 2) + " " + StatUnit.blocks.localized());
    }
    public void death(Unit unit){
        Wish = new FieldBulletType(damage, 30, radius){{
            lifetime = 320;
        }};
        Damage.damage(unit.x, unit.y, radius, damage*10);
        Wish.create(unit, unit.x, unit.y, 0);
        deathWish.at(unit.x, unit.y);
    }
}
